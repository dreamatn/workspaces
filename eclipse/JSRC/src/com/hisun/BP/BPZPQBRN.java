package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPQBRN {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_PARM_TYPE_TYPE = "PARMT";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG        ";
    char WS_BR_FLG = ' ';
    BPZPQBRN_WS_PARMT_VAL WS_PARMT_VAL = new BPZPQBRN_WS_PARMT_VAL();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPCPARM BPCPARM = new BPCPARM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPRPARM BPRPARM = new BPRPARM();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    SCCGWA SCCGWA;
    BPCPQBRN BPCPQBRN;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCPQBRN BPCPQBRN) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCPQBRN = BPCPQBRN;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPQBRN return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "PROGRAMME BEGAIN!");
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.CPY2CLS(SCCGWA, "BP0000", BPCPQBRN.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPQBRN);
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B200_MAIN_PROCESS();
        if (pgmRtn) return;
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCPQBRN.INPUT_DATA.TYPE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_INPUT_TYPE_CODE, BPCPQBRN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCPQBRN.INPUT_DATA.BRANCE == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BRANCE_MUST_INPUT, BPCPQBRN.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = BPCPQBRN.INPUT_DATA.BRANCE;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_RECORD_NOTFND)) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_NOTFND, BPCPQBRN.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B200_MAIN_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPARM);
        BPCPARM.PARM_DATA.PARM_FUNC = 'Q';
        BPCPARM.PARM_DATA.PARM_TYPE = K_PARM_TYPE_TYPE;
        BPCPARM.PARM_DATA.PARM_CODE = BPCPQBRN.INPUT_DATA.TYPE;
        CEP.TRC(SCCGWA, BPCPARM);
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPARM.PARM_FLG);
        if (BPCPARM.PARM_FLG == 'N') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_TYPE_NOTFND, BPCPQBRN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL, WS_PARMT_VAL);
        if (WS_PARMT_VAL.WS_PARMT_MAINT_BR1 == 0 
            && WS_PARMT_VAL.WS_PARMT_MAINT_BR2 == 0 
            && WS_PARMT_VAL.WS_PARMT_MAINT_BR3 == 0 
            && WS_PARMT_VAL.WS_PARMT_MAINT_BR4 == 0 
            && WS_PARMT_VAL.WS_PARMT_MAINT_BR5 == 0) {
            IBS.CPY2CLS(SCCGWA, "BP0000", BPCPQBRN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (WS_PARMT_VAL.WS_PARMT_MAINT_BR1 != 0 
            || WS_PARMT_VAL.WS_PARMT_MAINT_BR2 != 0 
            || WS_PARMT_VAL.WS_PARMT_MAINT_BR3 != 0 
            || WS_PARMT_VAL.WS_PARMT_MAINT_BR4 != 0 
            || WS_PARMT_VAL.WS_PARMT_MAINT_BR5 != 0) {
            WS_BR_FLG = 'O';
        }
        CEP.TRC(SCCGWA, WS_PARMT_VAL.WS_PARMT_MAINT_BR1);
        CEP.TRC(SCCGWA, WS_PARMT_VAL.WS_PARMT_MAINT_BR2);
        CEP.TRC(SCCGWA, WS_PARMT_VAL.WS_PARMT_MAINT_BR3);
        CEP.TRC(SCCGWA, WS_PARMT_VAL.WS_PARMT_MAINT_BR4);
        CEP.TRC(SCCGWA, WS_PARMT_VAL.WS_PARMT_MAINT_BR5);
        if (BPCPQBRN.INPUT_DATA.BRANCE == WS_PARMT_VAL.WS_PARMT_MAINT_BR1) {
            WS_BR_FLG = 'I';
        }
        if (BPCPQBRN.INPUT_DATA.BRANCE == WS_PARMT_VAL.WS_PARMT_MAINT_BR2) {
            WS_BR_FLG = 'I';
        }
        if (BPCPQBRN.INPUT_DATA.BRANCE == WS_PARMT_VAL.WS_PARMT_MAINT_BR3) {
            WS_BR_FLG = 'I';
        }
        if (BPCPQBRN.INPUT_DATA.BRANCE == WS_PARMT_VAL.WS_PARMT_MAINT_BR4) {
            WS_BR_FLG = 'I';
        }
        if (BPCPQBRN.INPUT_DATA.BRANCE == WS_PARMT_VAL.WS_PARMT_MAINT_BR5) {
            WS_BR_FLG = 'I';
        }
        CEP.TRC(SCCGWA, WS_BR_FLG);
        if (WS_BR_FLG == 'O') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_BRANCE_NOT_IN_AUTH, BPCPQBRN.RC);
        }
    }
    public void R000_PARM_DATA_PROCESS() throws IOException,SQLException,Exception {
        R000_PARM_DATA_PROCESS_NEW();
        if (pgmRtn) return;
        if (BPCPARM.PARM_RC.PARM_RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPARM.PARM_RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPQBRN.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQBRN.RC);
        CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_P_INQ_ORG, BPCPQORG);
    }
    public void R000_PARM_DATA_PROCESS_NEW() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPARM.PARM_DATA.PARM_FUNC);
        if (BPCPARM.PARM_DATA.PARM_FUNC == 'A' 
            && BPCPARM.PARM_DATA.PARM_EFF_DATE < SCCGWA.COMM_AREA.AC_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EFF_DATE_TOO_SMALL, BPCPARM.PARM_RC);
            S000_ERR_MSG_PROC_PARM();
            if (pgmRtn) return;
        }
        if (BPCPARM.PARM_DATA.PARM_EFF_DATE != 0 
            && BPCPARM.PARM_DATA.PARM_EXP_DATE != 0 
            && BPCPARM.PARM_DATA.PARM_EXP_DATE <= BPCPARM.PARM_DATA.PARM_EFF_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_EFF_EXP_DATE_ERROR, BPCPARM.PARM_RC);
            S000_ERR_MSG_PROC_PARM();
            if (pgmRtn) return;
        }
        if (BPCPARM.PARM_DATA.PARM_FUNC == 'Q') {
            IBS.init(SCCGWA, BPRPRMT);
            IBS.init(SCCGWA, BPCPRMM);
            BPRPRMT.KEY.TYP = BPCPARM.PARM_DATA.PARM_TYPE;
            BPRPRMT.KEY.CD = BPCPARM.PARM_DATA.PARM_CODE;
            if (BPCPARM.PARM_DATA.PARM_OPT_2 == 'O') {
                BPCPRMM.FUNC = '3';
            } else {
                BPCPRMM.FUNC = '4';
            }
            BPCPRMM.EFF_DT = BPCPARM.PARM_DATA.PARM_EFF_DATE;
            CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
            if (BPCPARM.PARM_DATA.PARM_EFF_DATE == 0) {
                BPCPRMM.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
            }
            CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
            BPCPRMM.EXP_DT = BPCPARM.PARM_DATA.PARM_EXP_DATE;
            S000_CALL_BPZPRMM();
            if (pgmRtn) return;
            BPCPARM.PARM_DATA.PARM_DESC = BPRPRMT.CDESC;
            BPCPARM.PARM_DATA.PARM_DESC_S = BPRPRMT.DESC;
            BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL = IBS.CLS2CPY(SCCGWA, BPRPRMT.DAT_TXT);
            BPCPARM.PARM_DATA.PARM_EFF_DATE = BPCPRMM.EFF_DT;
            BPCPARM.PARM_DATA.PARM_EXP_DATE = BPCPRMM.EXP_DT;
        }
        if (BPCPARM.PARM_DATA.PARM_FUNC == 'A') {
            IBS.init(SCCGWA, BPRPRMT);
            IBS.init(SCCGWA, BPCPRMM);
            BPRPRMT.KEY.TYP = BPCPARM.PARM_DATA.PARM_TYPE;
            BPRPRMT.KEY.CD = BPCPARM.PARM_DATA.PARM_CODE;
            BPRPRMT.DESC = BPCPARM.PARM_DATA.PARM_DESC_S;
            BPRPRMT.CDESC = BPCPARM.PARM_DATA.PARM_DESC;
            IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL, BPRPRMT.DAT_TXT);
            BPCPRMM.EFF_DT = BPCPARM.PARM_DATA.PARM_EFF_DATE;
            BPCPRMM.EXP_DT = BPCPARM.PARM_DATA.PARM_EXP_DATE;
            BPCPRMM.FUNC = '0';
            S000_CALL_BPZPRMM();
            if (pgmRtn) return;
        }
        if (BPCPARM.PARM_DATA.PARM_FUNC == 'U') {
            IBS.init(SCCGWA, BPRPRMT);
            IBS.init(SCCGWA, BPCPRMM);
            BPRPRMT.KEY.TYP = BPCPARM.PARM_DATA.PARM_TYPE;
            BPRPRMT.KEY.CD = BPCPARM.PARM_DATA.PARM_CODE;
            BPRPRMT.DESC = BPCPARM.PARM_DATA.PARM_DESC_S;
            BPRPRMT.CDESC = BPCPARM.PARM_DATA.PARM_DESC;
            IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL, BPRPRMT.DAT_TXT);
            CEP.TRC(SCCGWA, BPRPRMT.DAT_TXT);
            CEP.TRC(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL);
            BPCPRMM.EFF_DT = BPCPARM.PARM_DATA.PARM_EFF_DATE;
            BPCPRMM.EXP_DT = BPCPARM.PARM_DATA.PARM_EXP_DATE;
            CEP.TRC(SCCGWA, BPCPARM.PARM_DATA.PARM_EFF_DATE);
            CEP.TRC(SCCGWA, BPCPARM.PARM_DATA.PARM_EXP_DATE);
            CEP.TRC(SCCGWA, BPCPRMM.EFF_DT);
            CEP.TRC(SCCGWA, BPCPRMM.EXP_DT);
            BPCPRMM.FUNC = '2';
            S000_CALL_BPZPRMM();
            if (pgmRtn) return;
        }
        if (BPCPARM.PARM_DATA.PARM_FUNC == 'D') {
            IBS.init(SCCGWA, BPRPRMT);
            IBS.init(SCCGWA, BPCPRMM);
            BPRPRMT.KEY.TYP = BPCPARM.PARM_DATA.PARM_TYPE;
            BPRPRMT.KEY.CD = BPCPARM.PARM_DATA.PARM_CODE;
            BPCPRMM.EFF_DT = BPCPARM.PARM_DATA.PARM_EFF_DATE;
            BPCPRMM.EXP_DT = BPCPARM.PARM_DATA.PARM_EXP_DATE;
            BPCPRMM.FUNC = '1';
            S000_CALL_BPZPRMM();
            if (pgmRtn) return;
        }
        if (BPCPARM.PARM_DATA.PARM_FUNC == 'B') {
            if (BPCPARM.PARM_DATA.PARM_OPT_1 == 'S') {
                IBS.init(SCCGWA, BPRPARM);
                IBS.init(SCCGWA, BPCRMBPM);
                BPRPARM.KEY.TYPE = BPCPARM.PARM_DATA.PARM_TYPE;
                BPRPARM.KEY.CODE = BPCPARM.PARM_DATA.PARM_CODE;
                BPCRMBPM.FUNC = 'S';
                S000_CALL_BPZRMBPM();
                if (pgmRtn) return;
            }
            if (BPCPARM.PARM_DATA.PARM_OPT_1 == 'N') {
                IBS.init(SCCGWA, BPRPARM);
                IBS.init(SCCGWA, BPCRMBPM);
                BPCRMBPM.FUNC = 'R';
                S000_CALL_BPZRMBPM();
                if (pgmRtn) return;
                BPCPARM.PARM_DATA.PARM_TYPE = BPRPARM.KEY.TYPE;
                BPCPARM.PARM_DATA.PARM_CODE = BPRPARM.KEY.CODE;
                BPCPARM.PARM_DATA.PARM_DESC_S = BPRPARM.DESC;
                BPCPARM.PARM_DATA.PARM_DESC = BPRPARM.CDESC;
                BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL = BPRPARM.BLOB_VAL;
                BPCPARM.PARM_DATA.PARM_EFF_DATE = BPRPARM.EFF_DATE;
                BPCPARM.PARM_DATA.PARM_EXP_DATE = BPRPARM.EXP_DATE;
            }
            if (BPCPARM.PARM_DATA.PARM_OPT_1 == 'E') {
                IBS.init(SCCGWA, BPRPARM);
                IBS.init(SCCGWA, BPCRMBPM);
                BPCRMBPM.FUNC = 'E';
                S000_CALL_BPZRMBPM();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_CALL_BPZPRMM() throws IOException,SQLException,Exception {
        BPCPRMM.DAT_PTR = BPRPRMT;
        CEP.TRC(SCCGWA, BPRPRMT);
        CEP.TRC(SCCGWA, BPCPRMM);
        CEP.TRC(SCCGWA, BPCPARM.PARM_DATA.PARM_FUNC);
        CEP.TRC(SCCGWA, BPCPARM.PARM_DATA.PARM_OPT_2);
        CEP.TRC(SCCGWA, BPCPARM.PARM_DATA.PARM_TYPE);
        CEP.TRC(SCCGWA, BPCPARM.PARM_DATA.PARM_CODE);
        CEP.TRC(SCCGWA, BPCPARM.PARM_DATA.PARM_EFF_DATE);
        CEP.TRC(SCCGWA, BPCPARM.PARM_DATA.PARM_EXP_DATE);
        CEP.TRC(SCCGWA, "IDONTKNOW");
        IBS.CALLCPN(SCCGWA, "BP-PARM-MAINTAIN", BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM);
        CEP.TRC(SCCGWA, BPCPRMM.RC);
        if (BPCPRMM.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPARM.PARM_RC);
            if (BPCPARM.PARM_RC.PARM_RC_CODE == 180) {
                BPCPARM.PARM_FLG = 'N';
                IBS.init(SCCGWA, BPCPARM.PARM_RC);
            } else {
                S000_ERR_MSG_PROC_PARM();
                if (pgmRtn) return;
            }
        } else {
            if (BPCPRMM.FUNC == '3') {
                BPCPARM.PARM_FLG = 'E';
            }
        }
    }
    public void S000_CALL_BPZRMBPM() throws IOException,SQLException,Exception {
        BPCRMBPM.PTR = BPRPARM;
        IBS.CALLCPN(SCCGWA, "BP-R-MBRW-PARM", BPCRMBPM);
        CEP.TRC(SCCGWA, BPCRMBPM.RC);
        if (BPCRMBPM.RETURN_INFO == 'N' 
            || BPCRMBPM.RETURN_INFO == 'L') {
            BPCPARM.PARM_FLG = 'N';
        } else {
            if (BPCRMBPM.RC.RC_CODE != 0) {
                JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRMBPM.RC);
                IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCPARM.PARM_RC);
                S000_ERR_MSG_PROC_PARM();
                if (pgmRtn) return;
            }
        }
    }
    public void S000_ERR_MSG_PROC_PARM() throws IOException,SQLException,Exception {
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        Class<?>clazz = Class.forName(JIBS_tmp_str[9].trim());
        Object obj = clazz.newInstance();
        Method m = clazz.getDeclaredMethod("MP",new Class[]{SCCGWA.getClass(), SCCMPAG.getClass()});
        m.invoke(obj, SCCGWA, SCCMPAG);
        if (SCCGWA.COMM_AREA.EXCP_FLG == 'Y') {
            Z_RET();
            if (pgmRtn) return;
        }
    } else { //FROM #ELSE
    } //FROM #ENDIF
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "OUTPUT DATA:");
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCPQBRN.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCPQBRN = ");
            CEP.TRC(SCCGWA, BPCPQBRN);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
