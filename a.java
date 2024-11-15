// Source code is decompiled from a .class file using FernFlower decompiler.
package com.a.a.a;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class a {
   private static final String b = "https://webx-manage.opensdrm.net";
   private final String c = "/api/getToken";
   private final String d = "/api/authCheck";
   Gson a = (new GsonBuilder()).create();

   public a() {
   }

   private Map<String, String> a(String var1, String var2, boolean var3, String var4, String var5) {
      try {
         WebResource.Builder var6 = Client.create().resource(var1).type("application/json");
         if (var3) {
            var6 = (WebResource.Builder)var6.header("Authorization", var4);
         }

         ClientResponse var8;
         if ((var8 = (ClientResponse)var6.method(var5, ClientResponse.class, var2)).getStatus() != 200) {
            System.out.println("Response is not normal / " + var8.getStatus() + " (Response Code), " + var1 + " (Request URL),  Request Body : " + var2);
            return null;
         } else if ((var4 = (String)var8.getEntity(String.class)) == null) {
            System.out.println("Response data is null / " + var8.getStatus() + " (Response Code), " + var1 + " (Request URL),  Request Body : " + var2);
            return null;
         } else {
            return (Map)this.a.fromJson(var4, Map.class);
         }
      } catch (Exception var7) {
         System.out.println("request auth failed ::" + var7);
         return null;
      }
   }

   private String a(String var1, String var2) {
      HashMap var3;
      (var3 = new HashMap()).put("user_id", var1);
      var2 = (new e()).b(var2).toLowerCase();
      var3.put("password", var2);
      var1 = this.a.toJson(var3);
      Objects.requireNonNull(this);
      Map var4;
      if ((var4 = this.a("https://webx-manage.opensdrm.net" + "/api/getToken", var1, false, "", "POST")) == null) {
         System.out.println("Request for check auth failed; get token");
         return null;
      } else if (!((String)var4.get("code")).equals("R000")) {
         System.out.println("Invalid auth");
         return null;
      } else {
         return (String)var4.get("data");
      }
   }

   public com.a.b.a.b.a a(String var1, String var2, String var3, String var4, String var5, String var6) {
      var2 = this.a(var1, var2);
      HashMap var9;
      (var9 = new HashMap()).put("user_id", var1);
      var9.put("target_server_id", var3);
      if (var4 != null && !var4.isEmpty()) {
         var9.put("version", var4);
      }

      if (var6 != null && !var6.isEmpty()) {
         var9.put("url", var6);
      }

      com.a.b.a.b.a var7 = new com.a.b.a.b.a();
      var3 = this.a.toJson(var9);
      Objects.requireNonNull(this);
      Map var8;
      if ((var8 = this.a("https://webx-manage.opensdrm.net" + "/api/authCheck", var3, true, var2, "POST")) == null) {
         System.out.println("Request for check auth failed");
         var7.a("Request for check auth failed");
         return var7;
      } else if (((String)var8.get("code")).equals("R000")) {
         if (var8.containsKey("hash")) {
            var7.b((String)var8.get("hash"));
         }

         if (var8.containsKey("url")) {
            var7.d((String)var8.get("url"));
         }

         if (var8.containsKey("path")) {
            var7.c((String)var8.get("path"));
         }

         return var7;
      } else {
         String var10001 = (String)var8.get("desc");
         var7.a(var10001 + " / " + (String)var8.get("code"));
         return var7;
      }
   }

   public com.a.b.a.b.a b(String var1, String var2, String var3, String var4, String var5, String var6) {
      var2 = this.a(var1, var2);
      HashMap var9;
      (var9 = new HashMap()).put("user_id", var1);
      if (var5 != null && !var5.isEmpty()) {
         var9.put("version", var5);
      }

      if (var4 != null && !var4.isEmpty()) {
         var9.put("url", var4);
      }

      if (var3 != null && !var3.isEmpty()) {
         var9.put("system_info", var3);
      }

      com.a.b.a.b.a var7 = new com.a.b.a.b.a();
      var3 = this.a.toJson(var9);
      Objects.requireNonNull(this);
      Map var8;
      if ((var8 = this.a("https://webx-manage.opensdrm.net" + "/api/authCheck", var3, true, var2, "POST")) == null) {
         System.out.println("Request for check auth failed");
         var7.a("Request for check auth failed");
         return var7;
      } else if (((String)var8.get("code")).equals("R000")) {
         if (var8.containsKey("hash")) {
            var7.b((String)var8.get("hash"));
         }

         if (var8.containsKey("url")) {
            var7.d((String)var8.get("url"));
         }

         if (var8.containsKey("path")) {
            var7.c((String)var8.get("path"));
         }

         return var7;
      } else {
         String var10001 = (String)var8.get("desc");
         var7.a(var10001 + " / " + (String)var8.get("code"));
         return var7;
      }
   }
}
