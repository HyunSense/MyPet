package com.shopping.mypet.validation;

import jakarta.validation.GroupSequence;

@GroupSequence({NotBlankGroup.class, PatternGroup.class})
public interface ValidationSequence {
}
