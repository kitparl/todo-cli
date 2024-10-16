package com.todo;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {
  private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

  @Override
  public void write(JsonWriter out, LocalDateTime value) throws IOException {
    out.value(value != null ? value.format(formatter) : null);
  }

  @Override
  public LocalDateTime read(JsonReader in) throws IOException {
    String date = in.nextString();
    return date != null ? LocalDateTime.parse(date, formatter) : null;
  }
}
