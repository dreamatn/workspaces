package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZFTLPM {
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_PEND_MAINTAIN = "BP-R-PEND-MAINTAIN  ";
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY  ";
    String CPN_R_PRMR = "BP-PARM-READ        ";
    String K_PARM_TYPE = "PEND ";
    int K_MAX_COL = 99;
    int K_MAX_ROW = 15;
    int K_COL_CNT = 3;
    BPZFTLPM_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZFTLPM_WS_TEMP_VARIABLE();
    BPZFTLPM_WS_TLPM_HEAD WS_TLPM_HEAD = new BPZFTLPM_WS_TLPM_HEAD();
    char WS_TBL_PEND_FLAG = ' ';
    char WS_READNEXT_PEND_FLAG = ' ';
    char WS_DEL_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPRPEND BPRPEND = new BPRPEND();
    BPCRPENM BPCRPENM = new BPCRPENM();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCPEND BPCPEND = new BPCPEND();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCOTPEN BPCOTPEN = new BPCOTPEN();
    SCCGWA SCCGWA;
    BPCFTLPM BPCFTLPM;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCFTLPM BPCFTLPM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCFTLPM = BPCFTLPM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZFTLPM return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPRPEND);
        IBS.init(SCCGWA, BPCRPENM);
        IBS.CPY2CLS(SCCGWA, "BP0000", BPCFTLPM.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (BPCFTLPM.TLR.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCFTLPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = BPCFTLPM.TLR;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        if (BPCFTLRQ.INFO.TLR_TYP == 'S' 
            || BPCFTLRQ.INFO.TLR_TYP == 'C') {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NOT_COUNTER_TELLER, BPCFTLPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        if (BPCFTLPM.BUSS_TYP.trim().length() > 0) {
            IBS.init(SCCGWA, BPCPEND);
            IBS.init(SCCGWA, BPCPRMR);
            BPCPEND.KEY.CD = BPCFTLPM.BUSS_TYP;
            BPCPEND.KEY.TYP = "PEND ";
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
        }
        if (BPCFTLPM.OP_CODE == 'B') {
            CEP.TRC(SCCGWA, BPCFTLRQ.INFO.TLR_BR);
            CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
            if (BPCFTLRQ.INFO.TLR_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_NOTMATCH_ORG);
            }
        }
        if ((BPCFTLPM.OP_CODE == 'A' 
            || BPCFTLPM.OP_CODE == 'D') 
            && BPCFTLPM.TX_COUNT == 0) {
            BPCFTLPM.TX_COUNT = 1;
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        if (BPCFTLPM.OP_CODE == 'A'
            || BPCFTLPM.OP_CODE == 'D'
            || BPCFTLPM.OP_CODE == 'C') {
            C010_MOD_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCFTLPM.OP_CODE == 'Q') {
            C020_QRY_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCFTLPM.OP_CODE == 'B') {
            C030_BROWSE_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCFTLPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void C010_MOD_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPEND);
        BPRPEND.KEY.TLR = BPCFTLPM.TLR;
        BPRPEND.KEY.BUSS_TYP = BPCFTLPM.BUSS_TYP;
        CEP.TRC(SCCGWA, BPRPEND.KEY.TLR);
        CEP.TRC(SCCGWA, BPRPEND.KEY.BUSS_TYP);
        BPCRPENM.INFO.POINTER = BPRPEND;
        BPCRPENM.INFO.FUNC = 'R';
        BPCRPENM.INFO.LEN = 22;
        S000_CALL_BPZRPENM();
        if (pgmRtn) return;
        if (BPCRPENM.RETURN_INFO == 'N') {
            if (BPCFTLPM.OP_CODE == 'D' 
                || BPCFTLPM.OP_CODE == 'C') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_PEND_NOTFND, BPCFTLPM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            R000_ADD_TRANS_DATA_PARAMETER();
            if (pgmRtn) return;
            IBS.init(SCCGWA, BPCRPENM);
            BPCRPENM.INFO.POINTER = BPRPEND;
            BPCRPENM.INFO.FUNC = 'A';
            BPCRPENM.INFO.LEN = 22;
            S000_CALL_BPZRPENM();
            if (pgmRtn) return;
        } else if (BPCRPENM.RETURN_INFO == 'F') {
            R000_MOD_TRANS_DATA_PARAMETER();
            if (pgmRtn) return;
            IBS.init(SCCGWA, BPCRPENM);
            BPCRPENM.INFO.POINTER = BPRPEND;
            if (WS_DEL_FLG == 'Y') {
                BPCRPENM.INFO.FUNC = 'D';
            } else {
                BPCRPENM.INFO.FUNC = 'U';
            }
            BPCRPENM.INFO.LEN = 22;
            S000_CALL_BPZRPENM();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCFTLPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void C020_QRY_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPEND);
        BPRPEND.KEY.TLR = BPCFTLPM.TLR;
        BPRPEND.KEY.BUSS_TYP = BPCFTLPM.BUSS_TYP;
        BPCRPENM.INFO.POINTER = BPRPEND;
        BPCRPENM.INFO.FUNC = 'Q';
        BPCRPENM.INFO.LEN = 22;
        S000_CALL_BPZRPENM();
        if (pgmRtn) return;
    }
    public void C030_BROWSE_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPEND);
        BPRPEND.KEY.TLR = BPCFTLPM.TLR;
        BPCRPENM.INFO.POINTER = BPRPEND;
        BPCRPENM.INFO.FUNC = 'S';
        BPCRPENM.INFO.LEN = 22;
        S000_CALL_BPZRPENM();
        if (pgmRtn) return;
        WS_TEMP_VARIABLE.WS_CNT = 0;
        WS_TBL_PEND_FLAG = 'Y';
        while (WS_TBL_PEND_FLAG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            BPCRPENM.INFO.POINTER = BPRPEND;
            BPCRPENM.INFO.FUNC = 'N';
            BPCRPENM.INFO.LEN = 22;
            S000_CALL_BPZRPENM();
            if (pgmRtn) return;
            if (WS_TBL_PEND_FLAG == 'Y' 
                && BPRPEND.TX_COUNT != 0) {
                WS_TEMP_VARIABLE.WS_CNT += 1;
                if (WS_TEMP_VARIABLE.WS_CNT == 1) {
                    C030_01_OUT_TITLE();
                    if (pgmRtn) return;
                }
                C030_02_OUT_BRW_DATA();
                if (pgmRtn) return;
            }
        }
        BPCRPENM.INFO.POINTER = BPRPEND;
        BPCRPENM.INFO.FUNC = 'E';
        BPCRPENM.INFO.LEN = 22;
        S000_CALL_BPZRPENM();
        if (pgmRtn) return;
        if (WS_TEMP_VARIABLE.WS_CNT == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TLR_PEND_NOTFND, BPCFTLPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void C030_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 97;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void C030_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPEND);
        IBS.init(SCCGWA, BPCPRMR);
        BPCPEND.KEY.TYP = "PEND ";
        BPCPEND.KEY.CD = BPRPEND.KEY.BUSS_TYP;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        IBS.init(SCCGWA, BPCOTPEN);
        BPCOTPEN.TLR = BPRPEND.KEY.TLR;
        BPCOTPEN.BUSS_TYP = BPRPEND.KEY.BUSS_TYP;
        BPCOTPEN.BUSS_CDESC = BPCPEND.CDESC;
        BPCOTPEN.BUSS_DESC = BPCPEND.DESC;
        CEP.TRC(SCCGWA, BPCPEND.CDESC);
        BPCOTPEN.TX_COUNT = BPRPEND.TX_COUNT;
        BPCOTPEN.ACTION = BPCPEND.DATA_TXT.ACTION;
        CEP.TRC(SCCGWA, BPCOTPEN);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOTPEN);
        SCCMPAG.DATA_LEN = 97;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZFTLRQ() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_INF_QUERY, BPCFTLRQ);
        if (BPCFTLRQ.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTLRQ.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFTLPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        BPCPRMR.DAT_PTR = BPCPEND;
        IBS.CALLCPN(SCCGWA, CPN_R_PRMR, BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFTLPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRPENM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_PEND_MAINTAIN, BPCRPENM);
        if (BPCRPENM.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRPENM.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCFTLPM.RC);
            Z_RET();
            if (pgmRtn) return;
        } else {
            if (BPCRPENM.RETURN_INFO == 'N') {
                WS_TBL_PEND_FLAG = 'N';
            }
        }
    }
    public void R000_ADD_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPEND);
        BPRPEND.KEY.TLR = BPCFTLPM.TLR;
        BPRPEND.KEY.BUSS_TYP = BPCFTLPM.BUSS_TYP;
        BPRPEND.TX_COUNT = BPCFTLPM.TX_COUNT;
        BPRPEND.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
    }
    public void R000_MOD_TRANS_DATA_PARAMETER() throws IOException,SQLException,Exception {
        if (BPCFTLPM.OP_CODE == 'A') {
            BPRPEND.TX_COUNT += BPCFTLPM.TX_COUNT;
        } else if (BPCFTLPM.OP_CODE == 'D') {
            CEP.TRC(SCCGWA, BPCFTLPM.TX_COUNT);
            CEP.TRC(SCCGWA, BPRPEND.TX_COUNT);
            if (BPCFTLPM.TX_COUNT > BPRPEND.TX_COUNT) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCFTLPM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            BPRPEND.TX_COUNT = (short) (BPRPEND.TX_COUNT - BPCFTLPM.TX_COUNT);
            if (BPRPEND.TX_COUNT == 0) {
                WS_DEL_FLG = 'Y';
            }
        } else if (BPCFTLPM.OP_CODE == 'C') {
            BPRPEND.TX_COUNT = 0;
        } else {
        }
        BPRPEND.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
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
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCFTLPM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, " BPCFTLPM = ");
            CEP.TRC(SCCGWA, BPCFTLPM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
