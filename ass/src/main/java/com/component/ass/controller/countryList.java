package com.component.ass.controller;

import com.component.ass.utils.R;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.List;
//import org.apache.http.client.fluent.*;

@CrossOrigin
@RestController
@RequestMapping("/list")
public class countryList {

    @RequestMapping("/listHoliday1")
//    @RequiresPermissions("users:category:save")
    public void holidayList1() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://public-holiday.p.rapidapi.com/2019/CN"))
                .header("x-rapidapi-key", "1c8b5bbcf8mshb17f2d53d19506dp18dd79jsnd62a52a5b6ed")
                .header("x-rapidapi-host", "public-holiday.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    @RequestMapping("/list")
//    @RequiresPermissions("users:category:save")
    public R save(){
        return R.ok();
    }


    @RequestMapping("/listHoliday")
//    @RequiresPermissions("users:category:save")
    public R holidayList() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://public-holiday.p.rapidapi.com/2019/CN"))
                .header("x-rapidapi-key", "1c8b5bbcf8mshb17f2d53d19506dp18dd79jsnd62a52a5b6ed")
                .header("x-rapidapi-host", "public-holiday.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return R.ok().put("data", response);
    }

    @RequestMapping("/listHoliday2")
//    @RequiresPermissions("users:category:save")
    public R holidayList2() throws IOException, InterruptedException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://public-holiday.p.rapidapi.com/2019/US")
                .get()
                .addHeader("x-rapidapi-key", "1c8b5bbcf8mshb17f2d53d19506dp18dd79jsnd62a52a5b6ed")
                .addHeader("x-rapidapi-host", "public-holiday.p.rapidapi.com")
                .build();
        System.out.println(request.body());
        Response response = client.newCall(request).execute();
        return R.ok().put("data", response.body().toString());


    }

}





