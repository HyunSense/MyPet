package com.shopping.mypet.dto;

import jakarta.validation.GroupSequence;

@GroupSequence({NotBlankGroup.class, PatternGroup.class})
public interface ValidationSequence {
}
