
/**
 *
 * @author cihanogut
 * @since 15.10.2021
 *
 */
export const YarnService = function () {
    const actionType = {
        dataSource: [
            {id: 'fifo', name: 'FİFO'},
            {id: 'lifo', name: 'LİFO'},
            {id: 'siro', name: 'SİRO'},
        ],
        displayExpr: 'name',
        valueExpr: 'id',
    }
    const usageType = {
        dataSource: [
            {id: 'active', name: 'Aktif'},
            {id: 'passive', name: 'Pasif'},
            {id: 'sample', name: 'Numune'},
        ],
        displayExpr: 'name',
        valueExpr: 'id',
    }

    return {
        getActionType: function (){
            return actionType;
        },
        getUsageType: function (){
            return usageType;
        }
    }
};
