package com.crud.crud;

public class Crud {

    private long id;
    private String name;
    private String content;

    public long getId(){ return this.id; }

    public void setId( long id )   { this.id = id; }

    public String getName() { return this.name; }

    public void setName( String name ){ this.name = name; }

    public String getContent() { return this.content; }

    public void setContent( String content ){ this.content = content; }
}
