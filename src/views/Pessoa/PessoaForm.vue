<template>
    <div class="container">
  
  <div class="row">
   <div><p class="fs-3">Cadastrar novo usuário</p></div>
   <hr/>
  </div>
  
  <div v-if="mensagem.ativo" class="row">
   <div class="col-md-12 text-start">
     <div :class="mensagem.css" role="alert">
       <strong>{{ mensagem.titulo }}</strong> {{ mensagem.mensagem }}
       <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
     </div>
   </div>
  </div>
  
  <div class="row">
  <div class="col-md-12 text-start">
         <label class="form-label">Nome:</label>
         <input type="text" placeholder="Nome" class="form-control" v-model="pessoa.nome">
     </div>
     <div class="col-md-12 text-start">
         <label class="form-label">CPF: </label>
         <input type="text" placeholder="CPF" class="form-control" v-model="pessoa.cpf">
     </div>
     <div class="col-md-12 text-start">
         <label class="form-label">Telefone: </label>
         <input type="text" placeholder="Telefone" class="form-control" v-model="pessoa.telefoneEmergencia">
     </div>
     <div class="col-md-12 text-start">
         <label class="form-label">RG: </label>
         <input type="text" placeholder="RG" class="form-control" v-model="pessoa.rg">
     </div>
     <div class="col-md-12 text-start">
         <label class="form-label">Ano de nascimento:  </label>
         <input type="text" placeholder="Ano de nascimento" class="form-control" v-model="pessoa.dataNascimento">
     </div>
     <div class="col-md-12 text-start">
         <label class="form-label">Naturalidade: </label>
         <input type="text" placeholder="Naturalidade" class="form-control" v-model="pessoa.naturalidade">
     </div>
     <div class="col-md-12 text-start">
         <label class="form-label">Nacionalidade: </label>
         <input type="text" placeholder="Nacionalidade" class="form-control" v-model="pessoa.nacionalidade">
     </div>
     <div class="col-md-12 text-start">
         <label class="form-label">Escolaridade: </label>
         <select required class="form-select form-select-md  mb-3" aria-label=".form-select-md" v-model="pessoa.escolaridade">
            <option value="" disabled selected></option>
        <option option v-for="opcao in opcoesEscolaridade" :value="opcao" :key="opcao">{{ opcao }}</option>
    </select>
     </div>
     <div class="col-md-12 text-start">
         <label class="form-label">Sexo: </label>
         <select class="form-select form-select-md  mb-3" aria-label=".form-select-md" v-model="pessoa.sexo">
        <option option v-for="opcao in opcoesSexo" :value="opcao" :key="opcao">{{ opcao }}</option>
    </select>
     </div>
     <div class="col-md-12 text-start">
         <label class="form-label">Rua: </label>
         <input type="text" placeholder="Rua" class="form-control" v-model="pessoa.rua">
     </div>
     <div class="col-md-12 text-start">
         <label class="form-label">Bairro: </label>
         <input type="text" placeholder="Bairro" class="form-control" v-model="pessoa.bairro">
     </div>
     <div class="col-md-12 text-start">
         <label class="form-label">Nº Casa: </label>
         <input type="text" placeholder="Nº Casa" class="form-control" v-model="pessoa.numeroCasa">
     </div>
     <div class="col-md-12 text-start">
         <label class="form-label">CEP: </label>
         <input type="text" placeholder="CEP" class="form-control" v-model="pessoa.cep">
     </div>
  </div>
  
  <div class="row">
   <div class="col-md-3 offset-md-6"> 
     <div class="d-grid gap-2">
       <router-link type="button" id="voltarbt" class="btn btn-info" 
         to="/pessoa/lista">Voltar
       </router-link>
     </div>
   </div>
   <div class="col-md-3 "> 
     <div class="d-grid gap-2">
       <button v-if="this.form === undefined" type="button" 
           id="cadastrarbt" class="btn btn-success" @click="onClickCadastrar()">
         Cadastrar 
       </button>
       <button v-if="this.form === 'editar'" type="button" 
          id="editarbt" class="btn btn-warning" @click="onClickEditar()">
         Editar 
       </button>
       <button v-if="this.form === 'excluir'" type="button"
         id="excluirbt" class="btn btn-danger" @click="onClickExcluir()">
         Excluir 
       </button>
     </div>
   </div>
  </div>
  </div>
  
     
  </template>
  
  <script lang="ts">
  
  import { defineComponent } from 'vue';
import PessoaClient from '/Users/55459/OneDrive/Documentos/projeto-integrador-vue/project/src/client/PessoaClient';
import { Escolaridade } from '/Users/55459/OneDrive/Documentos/projeto-integrador-vue/project/src/model/EscolaridadeModel';
  import { Pessoa } from '/Users/55459/OneDrive/Documentos/projeto-integrador-vue/project/src/model/PessoaModel';
import { Sexo } from '/Users/55459/OneDrive/Documentos/projeto-integrador-vue/project/src/model/SexoModel';
  
  export default defineComponent({
   name: 'PessoaFormulario',
   /* eslint-disable */
   data() {
     return { 
       pessoa: new Pessoa(),
       escolaridadeSelecionada: '',
       sexoSelecionado: '',
       mensagem: {
         ativo: false as boolean,
         titulo: "" as string,
         mensagem: "" as string,
         css: "" as string
       }
     }
   },
   computed: {
     id () {
       return this.$route.query.id
     },
     form () {
       return this.$route.query.form
     },
     opcoesEscolaridade(): string[] {
    return Object.values(Escolaridade).map(escolaridade => String(escolaridade));
  },
  opcoesSexo(): string[] {
    return Object.values(Sexo).map(sexo => String(sexo));
  }
   },
   mounted() { 
  
     if(this.id !== undefined){
       this.findById(Number(this.id));
     }
  
   },
   methods: {

  
     onClickCadastrar(){
       PessoaClient.cadastrar(this.pessoa)
         .then(sucess => {
           this.pessoa = new Pessoa()
  
           
           this.mensagem.ativo = true;
           this.mensagem.mensagem = sucess;
           this.mensagem.css = "alert alert-success alert-dismissible fade show";
         })
         .catch(error => {
           this.mensagem.ativo = true;
           this.mensagem.mensagem = error;
           this.mensagem.titulo = "Error. ";
           this.mensagem.css = "alert alert-danger alert-dismissible fade show";
         });
     },
     findById(id: number){
       PessoaClient.findById(id)
         .then(sucess => {
           this.pessoa = sucess
         })
         .catch(error => {
           this.mensagem.ativo = true;
           this.mensagem.mensagem = error;
           this.mensagem.titulo = "Error. ";
           this.mensagem.css = "alert alert-danger alert-dismissible fade show";
         });
     },
     onClickEditar(){
       PessoaClient.editar(this.pessoa.id, this.pessoa)
         .then(sucess => {
           this.pessoa = new Pessoa()
           
           this.mensagem.ativo = true;
           this.mensagem.mensagem = sucess;
           this.mensagem.css = "alert alert-success alert-dismissible fade show";
         })
         .catch(error => {
           this.mensagem.ativo = true;
           this.mensagem.mensagem = error;
           this.mensagem.titulo = "Error. ";
           this.mensagem.css = "alert alert-danger alert-dismissible fade show";
         });
     },
     onClickExcluir(){
       PessoaClient.excluir(this.pessoa.id)
         .then(sucess => {
           this.pessoa = new Pessoa()
           this.mensagem.ativo = true;
           this.mensagem.mensagem = sucess;
           this.mensagem.css = "alert alert-success alert-dismissible fade show";
           
           
         })
         .catch(error => {
           this.mensagem.ativo = true;
           this.mensagem.mensagem = error;
           this.mensagem.titulo = "Error. ";
           this.mensagem.css = "alert alert-danger alert-dismissible fade show";
         });
     }
   }
  });
  
  
  </script>
  
  
  <style lang="scss">

  body{
   background-color: rgba(128, 128, 128, 0.089);
  }
      .col-md-10{
              font-size: 32px;
              font-weight: 600;
              font-family: 'Rubik', sans-serif;
          }

          .fs-3{
            margin-top: 20px;
            font-weight: 600;

            color: black
          }

          hr{
            color: black;
          }

          .container{
            line-height: 30px;
          }

          .row{
            line-height: 40px;
            font-weight: 600;
          }

          .form-label{
            color: black;
          }

          #voltarbt{
            margin-top: 20px;
            width: 100%;
          }

          #editarbt{
            margin-top: 20px;
          }

          #excluirbt{
            margin-top: 20px;
          }

          #cadastrarbt{
            margin-top: 20px;
          }
  </style>