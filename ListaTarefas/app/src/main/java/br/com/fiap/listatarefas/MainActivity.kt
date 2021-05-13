package br.com.fiap.listatarefas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var tarefas = ArrayList<String>()
    var adapter : ArrayAdapter<String>? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tarefas)
        lstTarefas.adapter = adapter

        fab.setOnClickListener{
            adicionarTarefa()
        }
        lstTarefas.setOnItemClickListener { parent, view, position, id ->
            val tarefa = parent.adapter.getItem(position).toString()
            atualizarTarefa(tarefa, position)
        }
    }

    fun adicionarTarefa(){
        //cria um AlertDialog para inserir a tarefa
        var builder = AlertDialog.Builder(this)
        builder.setTitle("Nova Tarefa")

        //Adicionar um editText ao AlertDialog
        val entrada = EditText(this)
        builder.setView(entrada)

        //Tratamento para quando o botão for pressionado
        builder.setPositiveButton("ok"){dialog, which ->
            tarefas.add(entrada.text.toString())
            adapter?.notifyDataSetChanged()

        }
        builder.setNegativeButton("Cancel", null)
        builder.create().show()
    }
    fun atualizarTarefa(tarefa:String, posicao: Int){
        var builder = AlertDialog.Builder(this)
        builder.setTitle("Atualizar Tarefa")
        var entrada = EditText(this)
        entrada.setText(tarefa, TextView.BufferType.EDITABLE)
        builder.setView(entrada)
        builder.setPositiveButton("ok"){
            dialog, which -> tarefas[posicao] = entrada.text.toString()
        }
        builder.setNegativeButton("cancel", null)
        builder.setNeutralButton("Excluir"){
            dialog, which ->
            tarefas.removeAt(posicao)
            adapter?.notifyDataSetChanged()
        }
        builder.create().show()
    }


}