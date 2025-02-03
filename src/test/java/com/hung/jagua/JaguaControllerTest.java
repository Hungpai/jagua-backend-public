package com.hung.jagua;

import com.hung.jagua.entity.*;
import com.hung.jagua.repository.DialogueRepository;
import com.hung.jagua.repository.ExampleRepository;
import com.hung.jagua.repository.GrammarRepository;
import com.hung.jagua.repository.VocabularyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class JaguaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private VocabularyRepository vocabularyRepository;

    @MockitoBean
    private ExampleRepository exampleRepository;

    @MockitoBean
    private DialogueRepository dialogueRepository;

    @MockitoBean
    private GrammarRepository grammarRepository;

    @Test
    public void testGetVocabularyByLection() throws Exception {
        // Arrange
        int lection = 1;
        Type type = Type.VOCABULARY;
        List<Vocabulary> dummyVocs =  Arrays.asList(
                new Vocabulary(type, List.of(), "わたし", "ich"),
                new Vocabulary(type, List.of(
                        new VocabularyKanji(1L, null,
                                new Kanji(1L, "人", "ひと"),
                                0)
                ), "あの人", "er, sie, die Person dort drüben")
        );
        when(vocabularyRepository.findAllByLectionAndTypeOrderById(lection, type)).thenReturn(dummyVocs);

        // Act
        ResultActions result = mockMvc.perform(get("/lection/voc/{lection}", lection));

        // Assert
        result.andExpect(status().isOk())
              .andExpect(jsonPath("$.length()").value(dummyVocs.size()))
              .andExpect(jsonPath("$[0].type").value(dummyVocs.get(0).getType().name()))
              .andExpect(jsonPath("$[0].kanji").isEmpty())
              .andExpect(jsonPath("$[0].word_jp").value(dummyVocs.get(0).getWord_jp()))
              .andExpect(jsonPath("$[0].word_de").value(dummyVocs.get(0).getWord_de()))
              .andExpect(jsonPath("$[1].kanji.length()").value(dummyVocs.get(1).getKanji().size()))
              .andExpect(jsonPath("$[1].kanji[0].kanji.kanji").value(dummyVocs.get(1).getKanji().get(0).getKanji().getKanji()))
              .andExpect(jsonPath("$[1].kanji[0].kanji.kana").value(dummyVocs.get(1).getKanji().get(0).getKanji().getKana()));
    }

    @Test
    void testGetPatternByLection() throws Exception {
        // Arrange
        int lection = 1;
        Type type = Type.PATTERN;
        List<Vocabulary> dummyPattern = Arrays.asList(
                new Vocabulary(type, List.of(), "わたしは マイク・ミラーです。", "Ich heiße Mike Miller. (wörtl. Ich bin Mike Miller.)")
        );
        when(vocabularyRepository.findAllByLectionAndTypeOrderById(lection, type)).thenReturn(dummyPattern);

        // Act
        ResultActions result = mockMvc.perform(get("/lection/pattern/{lection}", lection));

        // Assert
        result.andExpect((status().isOk()))
                .andExpect(jsonPath("$.length()").value(dummyPattern.size()));
    }

    @Test
    void testGetExampleByLection() throws Exception {
        // Arrange
        int lection = 1;
        Type type = Type.EXAMPLE;
        List<Example> dummyExample = Arrays.asList(
                new Example(
                        new Vocabulary(type, List.of(
                                new VocabularyKanji(1L, null, new Kanji(1L, "銀行員", "ぎんこういん"), 0)
                        ), "ワンさんは 銀行員ですか。", "Ist Herr Wang Bankangestellter?"),
                        new Vocabulary(type, List.of(
                                new VocabularyKanji(2L, null, new Kanji(1L, "ぎんこういん", "ぎんこういん"), 0),
                                new VocabularyKanji(3L, null, new Kanji(1L, "医者", "いしゃ"), 1)
                        ), "...いいえ、ワンさんは 銀行員じゃ ありません。医者です。", "...Nein, er ist kein Bankangestellter. Er is Arzt."),
                        type
                )
        );
        when(exampleRepository.findAllByLectionAndTypeOrderById(lection, type)).thenReturn(dummyExample);

        // Act
        ResultActions result = mockMvc.perform(get("/lection/example/{lection}", lection));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(dummyExample.size()))
                .andExpect(jsonPath("$[0].question.word_jp").value(dummyExample.get(0).getQuestion().getWord_jp()))
                .andExpect(jsonPath("$[0].question.word_de").value(dummyExample.get(0).getQuestion().getWord_de()))
                .andExpect(jsonPath("$[0].question.kanji[0].kanji.kanji").value(dummyExample.get(0).getQuestion().getKanji().get(0).getKanji().getKanji()))
                .andExpect(jsonPath("$[0].question.kanji[0].kanji.kana").value(dummyExample.get(0).getQuestion().getKanji().get(0).getKanji().getKana()))
                .andExpect(jsonPath("$[0].answer.word_jp").value(dummyExample.get(0).getAnswer().getWord_jp()))
                .andExpect(jsonPath("$[0].answer.word_de").value(dummyExample.get(0).getAnswer().getWord_de()))
                .andExpect(jsonPath("$[0].answer.kanji[0].kanji.kanji").value(dummyExample.get(0).getAnswer().getKanji().get(0).getKanji().getKanji()))
                .andExpect(jsonPath("$[0].answer.kanji[0].kanji.kana").value(dummyExample.get(0).getAnswer().getKanji().get(0).getKanji().getKana()))
                .andExpect(jsonPath("$[0].answer.kanji[0].position").value(dummyExample.get(0).getAnswer().getKanji().get(0).getPosition()))
                .andExpect(jsonPath("$[0].answer.kanji[1].position").value(dummyExample.get(0).getAnswer().getKanji().get(1).getPosition()));
    }

    @Test
    void testGetDialogueByLection() throws Exception {
        // Arrange
        int lection = 1;
        Type type = Type.DIALOGUE;
        Dialogue dialogue = new Dialogue(
                Arrays.asList(
                        new DialogueItem(1L, null, "Satō"),
                        new DialogueItem(2L, null, "Yamato"),
                        new DialogueItem(3L, null, "Satō"),
                        new DialogueItem(4L, null, "Miller")
                ),
                type,
                "Darf ich mich vorstellen"
        );
        when(dialogueRepository.findByLectionAndTypeOrderById(lection, type)).thenReturn(Optional.of(dialogue));

        // Act
        ResultActions result = mockMvc.perform(get("/lection/dialogue/{lection}", lection));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value(dialogue.getType().name()))
                .andExpect(jsonPath("$.title").value(dialogue.getTitle()))
                .andExpect(jsonPath("$.dialogue.length()").value(dialogue.getDialogue().size()));
    }

    @Test
    void testGetGrammarByLection() throws Exception {
        // Arrange
        int lection = 1;
        Type type = Type.GRAMMAR;
        List<Grammar> grammar = Arrays.asList(
                new Grammar(1L, List.of(
                        new GrammarItem(1L, null, "Die Partikel は zeigt an, dass das davostehende (N₁ ) das Thema des Satzes ist.", "Partikel は", ""),
                        new GrammarItem(2L, null, "Nomina in Verbindung mit です werden zu Prädikaten. です drückt eine Berurteilung oder Behauptung und gleichzeitig eine höfliche Einstellung gegenüber dem Hörer aus. Es flektiert be Verneinung und Vergangenheit.", "です", "")
                ), "", "N₁ は N₂ です", lection),
                new Grammar(2L, List.of(
                        new GrammarItem(3L, null, "じゃ（では）ありません ist die verneinte Form von です.", null, "は in では wird wie は ausgesprochen")
                ), "", "N₁ は N₂ じゃ (では) ありません", lection),
                new Grammar(3L, List.of(), "", "N₁ は N₂ ですか", lection),
                new Grammar(4L, List.of(), "", "N も", lection)
        );
        when(grammarRepository.findAllByLectionOrderById(lection)).thenReturn(grammar);

        // Act
        ResultActions result = mockMvc.perform(get("/lection/grammar/{lection}", lection));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(grammar.size()))
                .andExpect(jsonPath("$[0].header").value(grammar.get(0).getHeader()))
                .andExpect(jsonPath("$[0].grammarItems.length()").value(grammar.get(0).getGrammarItems().size()))
                .andExpect(jsonPath("$[0].grammarItems[0].title").value(grammar.get(0).getGrammarItems().get(0).getTitle()));
    }
}
