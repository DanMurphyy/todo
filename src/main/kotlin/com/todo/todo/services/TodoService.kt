package com.todo.todo.services

import com.todo.todo.exceptions.DataNotFoundException
import com.todo.todo.models.Todo
import com.todo.todo.repository.TodoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class TodoService {

    @Autowired
    lateinit var todoRepo: TodoRepository

    fun saveTodo(todoEntity: Todo): Todo {
        return todoRepo.save(todoEntity)
    }

    fun updateTodo(todoEntity: Todo, todoId: Long): Todo {
        //Boshida
//        val newTodo: Todo = todoRepo.getReferenceById(todoId)
//        newTodo.title = todoEntity.title
//        newTodo.description = todoEntity.description
//        newTodo.progress = todoEntity.progress
//        return todoRepo.save(newTodo)
        //Ohirida expcetionlar dan kegin
        if (todoEntity.id == todoId) {
            return try {
                val newTodo: Todo = todoRepo.getReferenceById(todoId)
                newTodo.title = todoEntity.title
                newTodo.description = todoEntity.description
                newTodo.progress = todoEntity.progress
                todoRepo.save(newTodo)
            } catch (e: Exception) {
                throw Exception("Unable to update data with id $todoId")
            }
        }
        throw DataNotFoundException("Id used not valid")

    }

    fun getAllTodo(): List<Todo>? {
        return todoRepo.findAll()
    }

    //Boshida
//    fun deleteTodoById(todoId: Long): String {
//        todoRepo.deleteById(todoId)
//        return "Deleted"
//    }
    //Ohirida expcetionlar dan kegin
    fun deleteTodoById(todoId: Long): ResponseEntity<Map<String, String>> {
        val existingTodo = todoRepo.findById(todoId)
        return if (existingTodo.isPresent) {
            try {
                todoRepo.deleteById(todoId)
                val dataMessage: MutableMap<String, String> = HashMap()
                dataMessage["data"] = "Properly Removed"
                ResponseEntity.ok(dataMessage)
            } catch (e: Exception) {
                // Handle other exceptions that might occur during deletion
                throw e
            }
        } else {
            // Handle the case where the item doesn't exist
            throw DataNotFoundException("Unable to delete item, id not found")
        }
    }


}
