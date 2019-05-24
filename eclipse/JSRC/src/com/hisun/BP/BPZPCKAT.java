package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZPCKAT {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    long WS_ACCT_CODE = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRACCT BPRACCT = new BPRACCT();
    BPCPARM BPCPARM = new BPCPARM();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPRPARM BPRPARM = new BPRPARM();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCOCKAT BPCOCKAT;
    public void MP(SCCGWA SCCGWA, BPCOCKAT BPCOCKAT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCOCKAT = BPCOCKAT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZPCKAT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, BPCOCKAT.RC);
        CEP.TRC(SCCGWA, BPCOCKAT.ACCT_CODE);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_ACCT_INFO();
        if (pgmRtn) return;
        B030_CHECK_ACCT();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCOCKAT.ACCT_CODE <= SPACE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_VCH_IPT_ERR, BPCOCKAT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_GET_ACCT_INFO() throws IOException,SQLException,Exception {
        S000_READ_BPTACCT();
        if (pgmRtn) return;
    }
    public void B030_CHECK_ACCT() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.AC_DATE < BPCPARM.PARM_DATA.PARM_EFF_DATE 
            || SCCGWA.COMM_AREA.AC_DATE > BPCPARM.PARM_DATA.PARM_EXP_DATE) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ACCT_NOT_VAL, BPCOCKAT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        BPCOCKAT.OFF_BAL_FLG = BPRACCT.DATA.OFF_BAL_FLG;
    }
    public void S000_READ_BPTACCT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPARM);
        IBS.init(SCCGWA, BPRACCT);
        BPCPARM.PARM_DATA.PARM_FUNC = 'Q';
        BPCPARM.PARM_DATA.PARM_OPT_2 = 'O';
        BPCPARM.PARM_DATA.PARM_TYPE = "ACCOT";
        WS_ACCT_CODE = BPCOCKAT.ACCT_CODE;
        BPCPARM.PARM_DATA.PARM_CODE = WS_ACCT_CODE;
        R000_PARM_DATA_PROCESS();
        if (pgmRtn) return;
        IBS.CPY2CLS(SCCGWA, BPCPARM.PARM_DATA.PARM_TXT.PARM_VAL, BPRACCT.DATA);
        if (BPCPARM.PARM_FLG == 'N') {
            CEP.TRC(SCCGWA, BPCPARM.PARM_FLG);
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_ACCT_NOTFND, BPCOCKAT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void R000_PARM_DATA_PROCESS() throws IOException,SQLException,Exception {
        R000_PARM_DATA_PROCESS_NEW();
        if (pgmRtn) return;
        if (BPCPARM.PARM_RC.PARM_RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPARM.PARM_RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCOCKAT.RC);
            Z_RET();
            if (pgmRtn) return;
        }
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
    public void Z_RET() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCOCKAT.RC);
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCOCKAT.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCOCKAT = ");
            CEP.TRC(SCCGWA, BPCOCKAT);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
