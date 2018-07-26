package net.transino.lms.web;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author lee
 * @since 5.0
 */
@Setter
@Getter
public class PostDataEntity {
    private String head;
    private String body;
    private String filename;
    private List filetext;
}