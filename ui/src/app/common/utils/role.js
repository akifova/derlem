import store from "@/store";

const pathMatchers = [
    {"path": "/home", "isPublic": true, "roles": []},

    {"path": "/general/brandOperations", isPublic: false, "roles": ["BRAND"]},
    {"path": "/general/uomOperations", isPublic: false, "roles": ["UOM"]},
    {"path": "/general/accountOperations", isPublic: false, "roles": ["USER"]},
    {"path": "/general/firmOperations", isPublic: false, "roles": ["FIRM"]},

    {"path": "/stockCard/shapeOperations", isPublic: false, "roles": ["STOCK_CARD"]},
    {"path": "/stockCard/qualityOperations", isPublic: false, "roles": ["STOCK_CARD"]},
    {"path": "/stockCard/paletteList", isPublic: false, "roles": ["STOCK_CARD"]},
    {"path": "/stockCard/palette", isPublic: false, "roles": ["STOCK_CARD"]},
    {"path": "/stockCard/collection", isPublic: false, "roles": ["STOCK_CARD"]},
    {"path": "/stockCard/patternCodeOperations", isPublic: false, "roles": ["STOCK_CARD"]},
    {"path": "/stockCard/sizeDefinitionOperations", isPublic: false, "roles": ["STOCK_CARD"]},
    {"path": "/stockCard/edge", isPublic: false, "roles": ["STOCK_CARD"]},

    {"path": "/yarn", isPublic: false, "roles": ["YARN"]},
    {"path": "/yarn/type", isPublic: false, "roles": ["YARN"]},
    {"path": "/yarn/property", isPublic: false, "roles": ["YARN"]},
    {"path": "/yarn/group", isPublic: false, "roles": ["YARN"]},

    {"path": "/specimen-yarn", isPublic: false, "roles": ["SAMPLE_YARN"]},
    {"path": "/creel", isPublic: false, "roles": ["CREEL"]},
    {"path": "/creelList", isPublic: false, "roles": ["CREEL"]},

    {"path": "/design/plan", isPublic: false, "roles": ["DESIGN_PLAN", "ARCHIVE_SAMPLE", "ARCHIVE_UNUSABLE", "ARCHIVE_STOCK", "ARCHIVE_RETIRED", "ARCHIVE_RD", "ARCHIVE_READ", "ARCHIVE_WRITE"]},

    {"path": "/archive/design", isPublic: false, "roles": ["ARCHIVE_WRITE"]},
    {"path": "/archive/design/list", isPublic: false, "roles": ["ARCHIVE_SAMPLE", "ARCHIVE_UNUSABLE", "ARCHIVE_STOCK", "ARCHIVE_RETIRED", "ARCHIVE_RD", "ARCHIVE_READ", "ARCHIVE_WRITE"]},
    {"path": "/archive/design/search", isPublic: false, "roles": ["ARCHIVE_READ"]}


];
export default {
    checkPath: function (pathValue) {
        const roles = store.getters._getCurrentUser?.roles;
        let userRoles = null;
        if (roles){
            userRoles = JSON.parse(roles);
        }
        //const userRoles = JSON.parse(store.getters._getCurrentUser?.roles);
        const pathInfo = pathMatchers.filter(f => f.path === pathValue);

        if(!userRoles) {
            return false;
        }

        if(pathInfo.isPublic) {
            return true;
        } else {
            let rolesFound = [];
            if(userRoles.indexOf("ADMIN") > -1 || userRoles.indexOf("KAZU") > -1) {
                return true;
            } else {
                for (let i = 0; i < userRoles.length; i++) {
                    let role = userRoles[i];
                    if(pathInfo[0].roles.indexOf(role) > -1) {
                        rolesFound.push(role);
                    }
                }
                return rolesFound.length > 0;
            }
        }
    }
}
