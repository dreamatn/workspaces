package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPNHIS;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class CIZSCGL {
    boolean pgmRtn = false;
    char WS_FUNC_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    CIRBAS CIRBAS = new CIRBAS();
    CIRCDM CIRCDM = new CIRCDM();
    CIRFDM CIRFDM = new CIRFDM();
    CIRBAS CIRBASO = new CIRBAS();
    CIRCDM CIRCDMO = new CIRCDM();
    CIRFDM CIRFDMO = new CIRFDM();
    CIRBAS CIRBASN = new CIRBAS();
    CIRCDM CIRCDMN = new CIRCDM();
    CIRFDM CIRFDMN = new CIRFDM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSCGL CICSCGL;
    public void MP(SCCGWA SCCGWA, CICSCGL CICSCGL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSCGL = CICSCGL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSCGL return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        if (WS_FUNC_FLG == '1') {
            B020_CHANGE_FDM_TO_CDM();
            if (pgmRtn) return;
        } else if (WS_FUNC_FLG == '2') {
            B030_CHANGE_CDM_TO_FDM();
            if (pgmRtn) return;
        } else if (WS_FUNC_FLG == '5') {
            B040_CHANGE_FIN_TYP();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "NORMAL INFORMATION INPUT ERROR");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, CICSCGL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSCGL.DATA.CI_NO);
        if (CICSCGL.DATA.CI_NO.trim().length() == 0) {
            CEP.TRC(SCCGWA, "CI-NO MUST INPUT");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_NO_MUST_INPUT, CICSCGL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICSCGL.DATA.CI_NO;
        T000_READ_CITBAS_EXIST();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "CI INF NOT EXIST");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND, CICSCGL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (CIRBAS.CI_TYP == '3' 
                && CICSCGL.DATA.CI_TYP_NEW == '2') {
            CEP.TRC(SCCGWA, "CHANGE FINANCIAL TO COMPANY");
            WS_FUNC_FLG = '1';
        } else if (CIRBAS.CI_TYP == '2' 
                && CICSCGL.DATA.CI_TYP_NEW == '3') {
            CEP.TRC(SCCGWA, "CHANGE COMPANY TO FINANCIAL");
            WS_FUNC_FLG = '2';
        } else if (CIRBAS.CI_TYP == '3' 
                && CICSCGL.DATA.FIN_TYPE_NEW.trim().length() > 0) {
            CEP.TRC(SCCGWA, "MODIFY FIN-TYPE");
            WS_FUNC_FLG = '5';
        } else {
            CEP.TRC(SCCGWA, "NORMAL INFORMATION INPUT ERROR");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, CICSCGL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_CHANGE_FDM_TO_CDM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        IBS.init(SCCGWA, CIRBASO);
        IBS.init(SCCGWA, CIRBASN);
        CIRBAS.KEY.CI_NO = CICSCGL.DATA.CI_NO;
        T000_READ_CITBAS_UPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRBAS, CIRBASO);
        CIRBAS.CI_TYP = CICSCGL.DATA.CI_TYP_NEW;
        CIRBAS.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRBAS.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRBAS.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_CITBAS();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRBAS, CIRBASN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        R000_WRITE_HIS_BAS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRFDM);
        IBS.init(SCCGWA, CIRFDMO);
        IBS.init(SCCGWA, CIRFDMN);
        CIRFDM.KEY.CI_NO = CICSCGL.DATA.CI_NO;
        T000_READ_CITFDM_UPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRFDM, CIRFDMO);
        IBS.init(SCCGWA, CIRCDM);
        IBS.init(SCCGWA, CIRCDMO);
        IBS.init(SCCGWA, CIRCDMN);
        R000_TRANS_FDM_TO_CDM();
        if (pgmRtn) return;
        T000_WRITE_CITCDM();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRCDM, CIRCDMN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        R000_WRITE_HIS_CDM();
        if (pgmRtn) return;
        T000_DELETE_CITFDM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        R000_WRITE_HIS_FDM();
        if (pgmRtn) return;
    }
    public void B030_CHANGE_CDM_TO_FDM() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSCGL.DATA.FIN_TYPE_NEW);
        if (CICSCGL.DATA.FIN_TYPE_NEW.trim().length() == 0) {
            CEP.TRC(SCCGWA, "FIN-TYPE MUST INPUT ");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FIN_TYPE_MUST_INPUT, CICSCGL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRBAS);
        IBS.init(SCCGWA, CIRBASO);
        IBS.init(SCCGWA, CIRBASN);
        CIRBAS.KEY.CI_NO = CICSCGL.DATA.CI_NO;
        T000_READ_CITBAS_UPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRBAS, CIRBASO);
        CIRBAS.CI_TYP = CICSCGL.DATA.CI_TYP_NEW;
        CIRBAS.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRBAS.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRBAS.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_CITBAS();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRBAS, CIRBASN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        R000_WRITE_HIS_BAS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRCDM);
        IBS.init(SCCGWA, CIRCDMO);
        IBS.init(SCCGWA, CIRCDMN);
        CIRCDM.KEY.CI_NO = CICSCGL.DATA.CI_NO;
        T000_READ_CITCDM_UPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRCDM, CIRCDMO);
        IBS.init(SCCGWA, CIRFDM);
        IBS.init(SCCGWA, CIRFDMO);
        IBS.init(SCCGWA, CIRFDMN);
        R000_TRANS_CDM_TO_FDM();
        if (pgmRtn) return;
        T000_WRITE_CITFDM();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRFDM, CIRFDMN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        R000_WRITE_HIS_FDM();
        if (pgmRtn) return;
        T000_DELETE_CITCDM();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'D';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        R000_WRITE_HIS_CDM();
        if (pgmRtn) return;
    }
    public void B040_CHANGE_FIN_TYP() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSCGL.DATA.FIN_TYPE_NEW);
        if (CICSCGL.DATA.FIN_TYPE_NEW.trim().length() == 0) {
            CEP.TRC(SCCGWA, "FIN-TYPE MUST INPUT");
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_FIN_TYPE_MUST_INPUT, CICSCGL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, CIRFDM);
        IBS.init(SCCGWA, CIRFDMO);
        IBS.init(SCCGWA, CIRFDMN);
        CIRFDM.KEY.CI_NO = CICSCGL.DATA.CI_NO;
        T000_READ_CITFDM_UPD();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRFDM, CIRFDMO);
        if (CICSCGL.DATA.ORG_TYPE_NEW.trim().length() > 0) {
            CIRFDM.ORG_TYPE = CICSCGL.DATA.ORG_TYPE_NEW;
        }
        CIRFDM.FIN_TYPE = CICSCGL.DATA.FIN_TYPE_NEW;
        CIRFDM.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRFDM.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRFDM.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_REWRITE_CITFDM();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRFDM, CIRFDMN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        R000_WRITE_HIS_FDM();
        if (pgmRtn) return;
    }
    public void R000_TRANS_FDM_TO_CDM() throws IOException,SQLException,Exception {
        CIRCDM.KEY.CI_NO = CIRFDM.KEY.CI_NO;
        CIRCDM.RESIDENT = CIRFDM.RESIDENT;
        CIRCDM.CI_SUB_TYP = CIRFDM.CI_SUB_TYP;
        if (CICSCGL.DATA.ORG_TYPE_NEW.trim().length() == 0) {
            CIRCDM.ORG_TYPE = CIRFDM.ORG_TYPE;
        } else {
            CIRCDM.ORG_TYPE = CICSCGL.DATA.ORG_TYPE_NEW;
        }
        CIRCDM.ECO = CIRFDM.ECO;
        CIRCDM.HECO = CIRFDM.HECO;
        CIRCDM.NED_TYP = CIRFDM.NED_TYP;
        CIRCDM.REG_DT = CIRFDM.REG_DT;
        CIRCDM.REG_CCY = CIRFDM.REG_CCY;
        CIRCDM.REG_AMT = CIRFDM.REG_AMT;
