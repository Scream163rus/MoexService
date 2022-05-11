package com.powerfulbenches.moexservice.parser;

import java.util.List;

public interface Parser<T> {
    List<T> parse(String xml);
}
