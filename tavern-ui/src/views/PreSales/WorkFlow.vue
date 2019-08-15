<template>
  <div id="mountNode"></div>
</template>

<script>
  import G6 from '@antv/g6';
  export default {
    name: "WorkFlow",
    created() { //不能在created方法中初始化
      //this.initG6()
    },
    mounted(){
      this.initG6()
    },
    methods: {
      initG6(){
        const data = {
          nodes: [{
            id: 'node1',
            x: 100,
            y: 200
          },{
            id: 'node2',
            x: 100,
            y: 400
          }],
          edges: [{
            target: 'node2',
            source: 'node1'
          }]
        };
        const graph = new G6.Graph({
          container: 'mountNode',
          width: 500,
          height: 500,
          nodeStyle: {
            default: {
              fill: '#40a9ff',
              stroke: '#096dd9'
            }
          },
          edgeStyle: {
            default: { stroke: '#A3B1BF' }
          }
        });
        graph.read(data);
      }
    }
  }
</script>
