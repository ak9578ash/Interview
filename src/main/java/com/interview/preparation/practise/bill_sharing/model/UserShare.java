package com.interview.preparation.practise.bill_sharing.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class UserShare{
    private final String emailId;
    private final Double share;
    private final List<Contribution> contributions;
}
