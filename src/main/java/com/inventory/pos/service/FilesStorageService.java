package com.inventory.pos.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FilesStorageService {
    public void init();

    public void save(MultipartFile file,String Name);

    public Resource load(String filename);

    public Stream<Path> loadAll();
}
