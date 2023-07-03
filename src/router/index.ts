import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/pessoa/lista',
    name: 'pessoa-lista-view',
    component: () => import(/* webpackChunkName: "about" */ '@/views/Pessoa/PessoaList.vue')
  },

  {
    path: '/pessoa/formulario',
    name: 'pessoa-formulario-view',
    component: () => import(/* webpackChunkName: "about" */ '@/views/Pessoa/PessoaForm.vue'),
    children: [
      {
        path: '/pessoa/formulario',
        name: 'pessoa-formulario-editar-view',
        component: () => import(/* webpackChunkName: "about" */ '@/views/Pessoa/PessoaForm.vue'),
      },
      {
        path: '/pessoa/formulario',
        name: 'pessoa-formulario-excluir-view',
        component: () => import(/* webpackChunkName: "about" */ '@/views/Pessoa/PessoaForm.vue')
      }
    ]
  },
  {
    path: '/admimistrador/lista',
    name: 'administrador-lista-view',
    component: () => import(/* webpackChunkName: "about" */ '@/views/Administrador/AdministradorList.vue')
  },

  {
    path: '/administrador/formulario',
    name: 'administrador-formulario-view',
    component: () => import(/* webpackChunkName: "about" */ '@/views/Administrador/AdministradorForm.vue'),
    children: [
      {
        path: '/administrador/formulario',
        name: 'administrador-formulario-editar-view',
        component: () => import(/* webpackChunkName: "about" */ '@/views//Administrador/AdministradorForm.vue'),
      },
      {
        path: '/administrador/formulario',
        name: 'administrador-formulario-excluir-view',
        component: () => import(/* webpackChunkName: "about" */ '@/views/Administrador/AdministradorForm.vue')
      }
    ]
  },
  {
    path: '/atividade/lista',
    name: 'atividade-lista-view',
    component: () => import(/* webpackChunkName: "about" */ '@/views/Atividades/AtividadesList.vue')
  },

  {
    path: '/atividade/formulario',
    name: 'atividade-formulario-view',
    component: () => import(/* webpackChunkName: "about" */ '@/views/Atividades/AtividadesForm.vue'),
    children: [
      {
        path: '/atividade/formulario',
        name: 'atividade-formulario-editar-view',
        component: () => import(/* webpackChunkName: "about" */ '@/views/Atividades/AtividadesForm.vue'),
      },
      {
        path: '/atividade/formulario',
        name: 'atividade-formulario-excluir-view',
        component: () => import(/* webpackChunkName: "about" */ '@/views/Atividades/AtividadesForm.vue')
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
