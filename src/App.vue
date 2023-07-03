<template>
  <nav>
    <div class="containerLogo">
      <img src="../public/logo.png">
      <a>Centro de Convivência do Idoso Afra Roth</a>
    </div>
    <router-link to="/pessoa/lista">Início</router-link>
    <router-link to="/atividade/lista">Agenda</router-link>
    <router-link to="/administrador/formulario">Perfil</router-link>
  </nav>
  <router-view/>
</template>

<style lang="scss">

#app {
  font-family:Rubik;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: white;
  text-align: center;
  color: #ffffff;
}
*{
  margin:0 auto;
  padding:0 auto;

}

nav {

  display: flex;
  width: 100vw;
  height: 14vh;
  background-color: #287261;
  padding: 20px;
 justify-content: space-between;
 

.containerLogo{
  display: flex;
  margin-right: 30rem;
  margin-left: 0;
  justify-content: left;
}
.containerLogo a{
  margin-right: 0;
}
  .containerLogo img {
    
    margin:0 auto;
    padding:0 auto;
    max-width:200px;
    max-height:100px;
    width: 130px;
    height: 75px;


    margin-top: -12px;
  }

  a {
   
    font-weight: bold;
    color: white;
    align-self:center;
    text-decoration: none;
    margin-right: 200px;
    

    &.router-link-exact-active {
     
      color: white;
    }
  }
}


// *{
//   margin: 0 auto;
//   padding: 0px;
  
//   font-family: 'Rubik', sans-serif;
// }

// .logo-prefeitura{
//   width: 50px;
//   height: 50px;
// }

// nav {
//     display: flex;
//     height: 10vh;
//     margin: auto;
//     justify-content: space-around;
//     align-items: center;
    
//     background-color: #287261;
//   }

// .header-class {
//   display: flex;
//   justify-content: center;
//   width: 5%;

//   color: #fff;
//   font-weight: 600;
//   text-decoration: none;
// }

// .titulo {
//   text-decoration: none;
//   color: white;

//   margin-top: 12px;
//   margin-left: 15px;
// }

// .routers-div {
//   display: flex;
//   width: 65%;
//   justify-content: space-around;
//   align-items: center;
// }

// .routers-class {
//   text-decoration: none;
//   color: white;

//   font-family: 'Rubik', sans-serif;
//   font-weight: 600;
// }

// #agenda{
//   margin-left: 20px;
// }

// // #app {
// //   font-family: Avenir, Helvetica, Arial, sans-serif;
// //   -webkit-font-smoothing: antialiased;
// //   -moz-osx-font-smoothing: grayscale;
// //   text-align: center;
// //   color: #2c3e50;
// // }

// // nav {
// //   padding: 30px;

// //   a {
// //     font-weight: bold;
// //     color: #2c3e50;

// //     &.router-link-exact-active {
// //       color: #42b983;
// //     }
// //   }
// // }
</style>
