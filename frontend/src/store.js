
import { createStore } from "vuex";
import createPersistedState from 'vuex-persistedstate';

const store = createStore({
    state() {
        return {
            pack: '',
            packselected:'',

        };
    },
    mutations: {
        setData(state, pack) {
            state.pack = pack;
        },
        setPackSelected(state, packselected){
            state.packselected=packselected;
        }

    },
    plugins: [createPersistedState()],
});

export default store;
