package com.example.kafkaexample.hls;

import com.example.kafkaexample.memory.HLSQueue;
import com.example.kafkaexample.memory.Packet;
import io.lindstrom.m3u8.model.MediaPlaylist;
import io.lindstrom.m3u8.model.MediaSegment;
import io.lindstrom.m3u8.parser.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@Configuration
public class HLSEnqueueScheduler {
    private final HLSService hlsService;
    private String maxVideoId = "";

    private String hlsTopic= "test";

    public HLSEnqueueScheduler(HLSService hlsService) {
        this.hlsService = hlsService;
    }

    @Scheduled(fixedRate = 10000) // Adjust the rate according to your needs (e.g., every 10 seconds)
    public void fetchAndEnqueHLSContent() throws Exception{
        byte[] hlsHeadFile = hlsService.fetchHLSFile(hlsTopic+".m3u8");
        if(hlsHeadFile!=null){

            HLSQueue.hlsQueue.add(new Packet(hlsTopic+".m3u8", hlsHeadFile));
            //System.out.println("HLS Enqueued!!!!!");
            fetchHLSfiles(hlsHeadFile);
        }
    }

    private void fetchHLSfiles(byte[] hlsContent) throws Exception{
        String masterPlaylist = new String(hlsContent);
        MediaPlaylistParser parser = new MediaPlaylistParser();
        MediaPlaylist playlist = parser.readPlaylist(masterPlaylist);
        List<MediaSegment> segmanets = playlist.mediaSegments();
        for(MediaSegment segment : segmanets){
            byte[] videofile=null;
            if(maxVideoId.compareTo(segment.uri())<0){
                maxVideoId = segment.uri();
                //System.out.println(segment.uri());
                videofile= hlsService.fetchHLSFile(segment.uri());
                if(videofile!=null){
                    HLSQueue.hlsQueue.add(new Packet(segment.uri(), videofile));
                }

            }
        }
    }
}