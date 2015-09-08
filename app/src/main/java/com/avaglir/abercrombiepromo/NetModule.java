package com.avaglir.abercrombiepromo;

import com.google.gson.Gson;

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
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.abercrombie.com").addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(NetService.class);
    }

    @Provides Gson provideGson() {
        return new Gson();
    }

//    public static class PromoAdapter extends TypeAdapter<Promo> {
//        @Override
//        public void write(JsonWriter out, Promo value) throws IOException {
//            throw new UnsupportedOperationException();
//        }
//
//        @Override
//        public Promo read(JsonReader in) throws IOException {
//            if (in.peek() == JsonToken.NULL) {
//                in.nextNull();
//                return null;
//            }
//            Promo promo = new Promo(null, null, null, null, null, null);
//            in.beginObject();
//            while (in.hasNext()) {
//                switch (in.nextName()) {
//                    case "button":
//                        readButton(in, promo);
//                        break;
//                    case "description":
//                        promo.setDescription(in.nextString());
//                        break;
//                    case "footer":
//                        promo.setDescription(in.nextString());
//                        break;
//                    case "image":
//                        promo.setImage(Uri.parse(in.nextString()));
//                        break;
//                    case "title":
//                        promo.setTitle(in.nextString());
//                        break;
//                }
//            }
//            in.endObject();
//            return promo;
//        }
//
//        public void readButton(JsonReader in, Promo promo) throws IOException {
//            Uri buttonUri = null;
//            String buttonTitle = null;
//            in.beginObject();
//            switch (in.nextName()) {
//                case "target":
//                    buttonUri = Uri.parse(in.nextString());
//                    in.nextName();
//                    buttonTitle = in.nextString();
//                    break;
//                case "title":
//                    buttonTitle = in.nextString();
//                    in.nextName();
//                    buttonUri = Uri.parse(in.nextString());
//                    break;
//            }
//            in.endObject();
//            promo.setButtonTarget(buttonUri);
//            promo.setButtonTitle(buttonTitle);
//        }
//    }
}
