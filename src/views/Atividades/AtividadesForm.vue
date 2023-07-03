<template>
    <div class="container">
  
  <div class="row">
   <div><p class="fs-3">Cadastrar nova Atividade</p></div>
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
         <label class="form-label">Nome da atividade:</label>
         <input type="text" placeholder="Nome" class="form-control" v-model="atividade.nomeAtividade">
     </div>
     <div class="col-md-12 text-start">
         <label class="form-label">Descrição: </label>
         <input type="text" id="descricao" placeholder="Escreva aqui.." class="form-control" v-model="atividade.descricao">
     </div>
  </div>
  
  <div class="row">
   <div class="col-md-3 offset-md-6"> 
     <div class="d-grid gap-2">
       <router-link type="button" id="voltarbt" class="btn btn-info" 
         to="/atividade/lista">Voltar
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
    import AtividadeClient from '/Users/55459/OneDrive/Documentos/projeto-integrador-vue/project/src/client/AtividadeClient';
    import { Atividade } from '/Users/55459/OneDrive/Documentos/projeto-integrador-vue/project/src/model/AtividadeModel';

  
  export default defineComponent({
   name: 'AtividadeFormulario',
   /* eslint-disable */
   data() {
     return { 
       atividade: new Atividade(),
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
     }
   },
   mounted() { 
  
     if(this.id !== undefined){
       this.findById(Number(this.id));
     }
  
   },
   methods: {

  
     onClickCadastrar(){
       AtividadeClient.cadastrar(this.atividade)
         .then(sucess => {
           this.atividade = new Atividade()
  
           
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
       AtividadeClient.findById(id)
         .then(sucess => {
           this.atividade = sucess
         })
         .catch(error => {
           this.mensagem.ativo = true;
           this.mensagem.mensagem = error;
           this.mensagem.titulo = "Error. ";
           this.mensagem.css = "alert alert-danger alert-dismissible fade show";
         });
     },
     onClickEditar(){
       AtividadeClient.editar(this.atividade.id, this.atividade)
         .then(sucess => {
           this.atividade = new Atividade()
           
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
       AtividadeClient.excluir(this.atividade.id)
         .then(sucess => {
           this.atividade = new Atividade()
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
      .col-md-10{
              font-size: 32px;
              font-weight: 600;
              font-family: 'Rubik', sans-serif;
          }

          .fs-3{
            margin-top: 20px;
            font-weight: 600;

            color: black;
          }

          hr{
            color: black;
          }

          .form-label{
            color: black;
          }

          #descricao{
            padding: 30px;
          }

          .container{
            line-height: 30px;
          }

          .row{
            line-height: 40px;
            font-weight: 600;
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