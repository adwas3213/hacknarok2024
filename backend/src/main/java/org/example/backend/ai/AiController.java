package org.example.backend.ai;

import lombok.RequiredArgsConstructor;
import org.example.backend.GeneratePostRequestDto;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class AiController {
    private final AiService aiService;
    private final IWasWrongAiService iWasWrongAiService;

    @PostMapping("/ai")
    private ChatResponse chatWithAi(@RequestBody String message) {
        return aiService.chatWithAi(message);
    }

    @PostMapping("/generatePostUsingMyStyle")
    private List<String> generatePostUsingMyStyle(@RequestBody GeneratePostRequestDto generatePostRequestDto) {
        if (generatePostRequestDto.myStyle()) {
            return aiService.generatePostUsingMyStyle(generatePostRequestDto.message());
        } else {
            return aiService.generatePostProposingSthNew(generatePostRequestDto.message());
        }
    }

    //TODO test it with accurate mock data
    @PostMapping("/ai/myMistakes")
    private ChatResponse showMyMistakes(@RequestBody String message) {
        return iWasWrongAiService.whereIHasMadeMistake(message);
    }

    //TODO test it with accurate mock data
    @PostMapping("/ai/conclusions")
    private ChatResponse showConclusions(@RequestBody String message) {
        return iWasWrongAiService.whatCanIImproveMyFuturePosts(message);
    }
}
