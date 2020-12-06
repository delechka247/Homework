package ru.itis.javalab;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class Args {
    @Parameter(names = {"--server-ip"})
    public String ip;

    @Parameter(names = {"--server-port"})
    public int port;
}