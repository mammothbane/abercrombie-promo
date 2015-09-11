package com.avaglir.abercrombiepromo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by mammothbane on 9/7/2015.
 */

@Module
public class NetModule {
    @Provides NetService provideNetService(Gson gson) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.abercrombie.com").addConverterFactory(GsonConverterFactory.create(gson)).build();
        return retrofit.create(NetService.class);
    }

    @Provides Gson provideGson() {
        return new GsonBuilder().registerTypeAdapter(Promo.class, new PromoAdapter()).create();
    }

    /**
     * a gson type adapter for the promo class. this is necessary because the json we receive from
     * the endpoint isn't consistent in formatting the button object. sometimes it's an object
     * with two members, and sometimes it's an array containing that single object:
     * "button" : {} vs "button" : [ {} ]
     */
    public static class PromoAdapter extends TypeAdapter<Promo> {

        /**
         * If we want to serialize to Json, the default Gson serializer will suffice.
         * @param out the <code>JsonWriter</code> to write the tokens to.
         * @param value the promo to serialize.
         * @throws IOException
         */
        @Override
        public void write(JsonWriter out, Promo value) throws IOException {
            throw new UnsupportedOperationException();
        }

        /**
         *
         * @param in The reader to read the tokens from.
         * @return Return the serialzed <code>Promo</code> or <code>null</code>, if there was nothing in <code>in</code>.
         * @throws IOException
         */
        @Override
        public Promo read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            Promo promo = new Promo(null, null, null, null, null, null);
            in.beginObject();
            while (in.hasNext()) {
                switch (in.nextName()) {
                    case "button":
                        readButton(in, promo);
                        break;
                    case "description":
                        promo.setDescription(in.nextString());
                        break;
                    case "footer":
                        promo.setFooter(in.nextString());
                        break;
                    case "image":
                        promo.setImage(in.nextString());
                        break;
                    case "title":
                        promo.setTitle(in.nextString());
                        break;
                }
            }
            in.endObject();
            return promo;
        }

        public void readButton(JsonReader in, Promo promo) throws IOException {
            String buttonTarget = null;
            String buttonTitle = null;
            boolean isNestedArray = false;
            if (in.peek() == JsonToken.BEGIN_ARRAY) {
                isNestedArray = true;
                in.beginArray();
            }
            in.beginObject();
            switch (in.nextName()) {
                case "target":
                    buttonTarget = in.nextString();
                    in.nextName();
                    buttonTitle = in.nextString();
                    break;
                case "title":
                    buttonTitle = in.nextString();
                    in.nextName();
                    buttonTarget = in.nextString();
                    break;
            }
            in.endObject();
            if (isNestedArray) in.endArray();
            promo.setButtonTarget(buttonTarget);
            promo.setButtonTitle(buttonTitle);
        }
    }
}
