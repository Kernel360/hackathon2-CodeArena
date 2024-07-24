package sample.codearea.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sample.codearea.dto.CommentRequestDto;
import sample.codearea.dto.CommentResponseDto;
import sample.codearea.service.CommentService;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = "http://localhost:8080/comment")
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;

	@GetMapping("")
	public List<CommentResponseDto> getCommentList(
		@RequestParam Long answerId
	){
		return commentService.findByAnswerId(answerId);
	}

	@PostMapping("")
	public void addComment(
		@RequestBody CommentRequestDto commentRequestDto
	) {
		commentService.save(commentRequestDto);
	}

	@DeleteMapping("")
	public void addComment(
		@RequestParam Long commentId
	) {
		commentService.delete(commentId);
	}

	@PutMapping("/{commentId}")
	public void updateComment(
		@PathVariable Long commentId,
		@RequestBody CommentRequestDto commentRequestDto
	){
		commentService.update(commentId,commentRequestDto);
	}

}
