<template>
  <div class = "main-container" style="margin-top: 10px;">
     <div class="row">
        <div class="col-md-10 text-start">
           <p class="fs-3">Cadastros</p>
        </div>
        <div class="col-md-2">
           <div class="d-grid gap-2">
              <router-link id="bt-cadastro" type="button" class="btn btn-success" 
                 to="/pessoa/formulario">Cadastrar
              </router-link>
           </div>
        </div>
        <div class="row">    
      <div class="col-xs-8 col-xs-offset-2">
      <div class="input-group">
              <div class="input-group-btn search-panel">
              </div>
              <input type="hidden" name="search_param" value="all" id="search_param">         
              <input type="text" class="form-control" name="x" placeholder="Procurar..">
              <span class="input-group-btn">
                  <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span></button>
              </span>
          </div>
      </div>
</div>
     </div>
     <div class="row">
           <table class="table table-striped">
              <thead class="table-secondary">
                 <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Ativo</th>
                    <th scope="col">CPF</th>
                    <th scope="col">Telefone</th>
                    <th scope="col">Nome</th>
                    <th scope="col">Opcões</th>
                 </tr>
              </thead>
              <tbody class="table-group-divider">
                 <tr v-for="item in pessoasList" :key="item.id">
                    <th class="col-md-1">{{ item.id }}</th>
                    <th class="col-md-2"> 
                       <span v-if="item.ativo" class="badge text-bg-success"> Ativo </span>
                       <span v-if="!item.ativo" class="badge text-bg-danger"> Inativo </span>
                    </th>
                    <th >{{ item.cpf }}</th>
                    <th >{{ item.telefoneEmergencia }}</th>
                    <th >{{ item.nome }}</th>
                    <th class="col-md-2">
                       <div class="btn-group" role="group" aria-label="Basic mixed styles example">
                          <router-link type="button" class="btn btn-sm btn-warning" 
                             :to="{ name: 'pessoa-formulario-editar-view', query: { id: item.id, form: 'editar' } } "> 
                             <i class="ri-pencil-line"></i> 
                          </router-link>
                          <router-link type="button" class="btn btn-sm btn-danger" 
                             :to="{ name: 'pessoa-formulario-excluir-view', query: { id: item.id, form: 'excluir' } } ">
                             <i class="ri-delete-bin-line"></i> 
                          </router-link>
                       </div>
                    </th>
                 </tr>
              </tbody>
           </table>
     </div>
  </div>
</template>

<script lang="ts">
import PessoaClient from "/Users/55459/OneDrive/Documentos/projeto-integrador-vue/project/src/client/PessoaClient";
import { Pessoa } from "/Users/55459/OneDrive/Documentos/projeto-integrador-vue/project/src/model/PessoaModel";
import { defineComponent } from "vue";

export default defineComponent({
  name: 'CondutoresLista',
  data() {
    return {
        pessoasList: new Array<Pessoa>()
    }
  },
  mounted() {
    this.findAll();
  },
  methods: {

    findAll() {
      PessoaClient.listaAll()
        .then(sucess => {
          this.pessoasList = sucess
        })
        .catch(error => {
          console.log(error);
        });
    }
  }
});
</script>



<style lang="scss">

body{
  background-color: rgba(128, 128, 128, 0.089)
}

.main-container{
  width: 95%;
  
}

.fs-3{
  font-weight: 600;
  font-size: 30px;
  color: black;

  margin-top: 15px;
}

.input-group{
  width: 100%;

  margin-top: 20px;
  margin-bottom: 25px;


}

#bt-cadastro{
  margin-top: 10px;

  border-radius: 10px;
  width: 80%;

  border: none;

  font-weight: 600;
}

.table-striped{
  border: 0.5px solid rgba(0, 0, 0, 0.308);
}

</style>