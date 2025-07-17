package com.alc.bookstore.bms.metrics.application.books.counter;

import com.alc.bookstore.shared.domain.bus.command.Command;
import lombok.Data;

@Data
public class IncreaseBooksCounterCommand implements Command {}
