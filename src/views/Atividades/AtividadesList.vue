<template>
<div class = "row" id="titulo">Lista de atividades</div>
<div class="conteudo">
   <div class="topo">
      <router-link to="/atividade/formulario"><button id="addtarefa"><i class="fa fa-plus"></i>Adicionar Atividade</button></router-link>
   </div>
   <ul id = "listatarefas">
      <li v-for="item in atividadesList" :key="item.id">
         <span>{{ item.nomeAtividade }}</span>
         <div class="btn-group" role="group" aria-label="Basic mixed styles example">
            <router-link type="button" class="btn btn-sm btn-warning" 
               :to="{ name: 'atividade-formulario-editar-view', query: { id: item.id, form: 'editar' } } "> 
               <i class="ri-pencil-line"></i> 
            </router-link>
            <router-link type="button" class="btn btn-sm btn-danger" 
               :to="{ name: 'atividade-formulario-excluir-view', query: { id: item.id, form: 'excluir' } } ">
               <i class="ri-delete-bin-line"></i> 
            </router-link>
         </div>
      </li>
   </ul>
</div>

</template>

<script lang="ts">
import AtividadeClient from "/Users/55459/OneDrive/Documentos/projeto-integrador-vue/project/src/client/AtividadeClient";
import { Atividade } from "/Users/55459/OneDrive/Documentos/projeto-integrador-vue/project/src/model/AtividadeModel";
import { defineComponent } from "vue";

export default defineComponent({
    name: 'CondutoresLista',
    data() {
      return {
          atividadesList: new Array<Atividade>()
      }
    },
    mounted() {
      this.findAll();
    },
    methods: {
  
      findAll() {
        AtividadeClient.listaAll()
          .then(sucess => {
            this.atividadesList = sucess
          })
          .catch(error => {
            console.log(error);
          });
      }
    }
  });
</script>


<style lang="scss" scoped>

body{
    background-color: rgba(128, 128, 128, 0);
}

#titulo{
   font-size: 24px;
   margin: 0 auto;
   margin-left: 580px;

   margin-top: 20px;
}

.conteudo {
   width: 100%;
   max-width: 450px;
   margin-top: 25px;
   background-color: rgba(128, 128, 128, 0.075);
   padding: 35px;
   border-radius: 60px;
}

.textoTarefa{
   color: black;
}

.topo{
   display:flex;
   justify-content: space-around;
   margin-bottom: 35px;
}

.topo input {
   width: 350px;
   padding: 15px;
   border-radius: 12px;
   border: 1px solid #191818;
   outline: none;
   font-size: 1.2rem;
   background: #191818;
   color: black;
}

.topo button{
   width: 100%;
   border-radius: 12px;
   height: 40px;

   border: 1px solid #191818;
   background: #157347;
   color: white;
   outline: none;
}

.topo button:hover{
   background: #818181;
   cursor: pointer;
}

#listatarefas{
   list-style-type: none;
   padding: 0;
   margin: 0;
}

#listatarefas li{
   padding: 20px;
   background: white;
   border-radius: 12px;

   display: flex;
   justify-content: space-between;
   align-items: center;
   margin-top: 20px;

   color: black;
   font-weight: 600;
}

.btnAcao{
   border-radius: 20px;
   border: 1px solid #191818;
   
   width: 38px;
   height: 38px;

   margin-right: 8px;
   cursor: pointer;
   background: #CAA022;
   color: black;

}

.btnExcluir{
   border-radius: 20px;
   border: 1px solid #191818;
   
   width: 38px;
   height: 38px;

   margin-right: 8px;
   cursor: pointer;
   background: #BB2D3B;
   color: white;
}



.row{
   
   text-align: center;
   

   font-weight: 600;
   font-weight: 24px;
   color: black;
}
</style>