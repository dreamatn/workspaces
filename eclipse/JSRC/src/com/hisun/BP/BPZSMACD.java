package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class BPZSMACD {
    boolean pgmRtn = false;
    String K_CMP_TXN_HIS = "BP-REC-NHIS";
    String K_HIS_REMARKS = "ANALYSIS CODE MAINTENANCE";
    String K_HIS_COPYBOOK_NAME = "BPCHACD";
    String K_PARM_CODE_TYPE = "PANCD";
    String K_FMT_CD_1 = "BPX01";
    String K_FMT_CD_2 = "BP352";
    short K_FLD_MAX_LEN = 25;
    BPZSMACD_WS_RC WS_RC = new BPZSMACD_WS_RC();
    short WS_SEQ = 0;
    String WS_DISPLAY_SEQ = " ";
    short WS_I = 0;
    short WS_CD = 0;
    short WS_CNT = 0;
    int WS_TXN_CD = 0;
    BPZSMACD_WS_PARM_KEY WS_PARM_KEY = new BPZSMACD_WS_PARM_KEY();
    char WS_OUTPUT_FLG = ' ';
    char WS_GET_SEQ_FLG = ' ';
    char WS_STOP_FLG = ' ';
    char WS_FIT_COND_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    BPRPRMT BPRPRMT = new BPRPRMT();
    BPRPRMT BPRBPRMT = new BPRPRMT();
    BPRPARM BPRPARM = new BPRPARM();
    BPCPRMM BPCPRMM = new BPCPRMM();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPCOQPCD BPCOQPCD = new BPCOQPCD();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRTRT BPRTRT = new BPRTRT();
    BPCOMACD BPCOMACD = new BPCOMACD();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCHACD BPCOHACD = new BPCHACD();
    BPCHACD BPCNHACD = new BPCHACD();
    TCCASMSG TCCASMSG = new TCCASMSG();
    BPRMACD BPRRMACD = new BPRMACD();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    SCCGWA SCCGWA;
    BPCSMACD BPCSMACD;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, BPCSMACD BPCSMACD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSMACD = BPCSMACD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSMACD return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, BPCOHACD);
        WS_GET_SEQ_FLG = 'N';
        BPCSMACD.PARM_DATA.FLD_MAX_LEN = K_FLD_MAX_LEN;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSMACD);
        B100_CHECK_INPUT();
        if (pgmRtn) return;
        B110_GET_INPUT_DATA();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSMACD.FUNC);
        if (BPCSMACD.FUNC == 'G' 
            || BPCSMACD.FUNC == 'D') {
            B200_GET_SEQ();
            if (pgmRtn) return;
        }
        if (BPCSMACD.FUNC == 'Q'
            || BPCSMACD.FUNC == 'A'
            || BPCSMACD.FUNC == 'U'
            || BPCSMACD.FUNC == 'C'
            || BPCSMACD.FUNC == 'D') {
            B210_KEY_PROCESS();
            if (pgmRtn) return;
        } else if (BPCSMACD.FUNC == 'B') {
            B220_BROWSE_PROCESS();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (!(BPCSMACD.OUTPUT_FLG == 'Y' 
            || BPCSMACD.OUTPUT_FLG == 'N')) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSMACD.PARM_DATA.FLD_LEN > 25) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_FIELD_LENGHT_BIGGER, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSMACD.SEQ_NO > 10) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_SEQ_TOO_BIGGER, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, "SMACD-OUTPUT-FLG");
        if (BPCSMACD.TXN_CD.trim().length() == 0 
            && (BPCSMACD.FUNC != 'B')) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (BPCSMACD.FUNC == 'G') {
            IBS.init(SCCGWA, BPRTRT);
            IBS.init(SCCGWA, BPCPRMR);
            BPRTRT.KEY.TYP = "TRT";
            BPRTRT.KEY.CD = BPCSMACD.TXN_CD;
            BPCPRMR.DAT_PTR = BPRTRT;
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
            if (BPCPRMR.RC.RC_RTNCODE != 0) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TXN_CODE_NOT_EXIST, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        WS_CNT = 0;
        if (BPCSMACD.TXN_CD.trim().length() > 0 
            && BPCSMACD.FUNC != 'G') {
            for (WS_CD = 1; WS_CD <= 7; WS_CD += 1) {
                if (BPCSMACD.TXN_CD == null) BPCSMACD.TXN_CD = "";
                JIBS_tmp_int = BPCSMACD.TXN_CD.length();
                for (int i=0;i<7-JIBS_tmp_int;i++) BPCSMACD.TXN_CD += " ";
                if () {
                    WS_CNT = (short) (WS_CNT + 1);
                }
            }
            CEP.TRC(SCCGWA, BPCSMACD.TXN_CD);
            CEP.TRC(SCCGWA, WS_CNT);
            if (WS_CNT != 7) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TXTCODE_IS_NUMERIC, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        CEP.TRC(SCCGWA, BPCSMACD.TXN_CD);
        CEP.TRC(SCCGWA, "3");
        if (BPCSMACD.FUNC == 'A' 
            || BPCSMACD.FUNC == 'U') {
            if ((BPCSMACD.TXN_CD.trim().length() == 0 
                || BPCSMACD.PARM_DATA.FLD_DESC.trim().length() == 0 
                || BPCSMACD.PARM_DATA.FLD_DESCE.trim().length() == 0 
                || BPCSMACD.PARM_DATA.FLD_TYPE == ' ' 
                || BPCSMACD.PARM_DATA.FLD_IPT_FLG == ' ') 
                || (BPCSMACD.PARM_DATA.FLD_LEN == 0)) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (BPCSMACD.PARM_DATA.FLD_TYPE != '9' 
                && BPCSMACD.PARM_DATA.FLD_TYPE != 'X') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, BPCSMACD.PARM_DATA.FLD_TYPE);
            CEP.TRC(SCCGWA, BPCSMACD.PARM_DATA.FLD_IPT_FLG);
            if (BPCSMACD.PARM_DATA.FLD_IPT_FLG != 'M' 
                && BPCSMACD.PARM_DATA.FLD_IPT_FLG != 'O') {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_H_INPUT_ERROR, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, BPCSMACD.PARM_DATA.PBCD_TYPE);
            if (BPCSMACD.PARM_DATA.PBCD_TYPE.trim().length() > 0) {
                IBS.init(SCCGWA, BPCOQPCD);
                BPCOQPCD.INPUT_DATA.TYPE = BPCSMACD.PARM_DATA.PBCD_TYPE;
                S000_CALL_BPZPQPCD();
                if (pgmRtn) return;
            }
            B010_CHECK_DIS_SEQ_PROC();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_DIS_SEQ_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPARM);
        IBS.init(SCCGWA, BPCRMBPM);
        BPRPARM.KEY.TYPE = K_PARM_CODE_TYPE;
        BPRPARM.KEY.CODE = BPCSMACD.TXN_CD;
        BPCRMBPM.FUNC = 'S';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
        for (WS_I = 1; (WS_I <= 10) 
            && WS_STOP_FLG != 'Y'; WS_I += 1) {
            IBS.init(SCCGWA, BPRPARM);
            IBS.init(SCCGWA, BPCRMBPM);
            BPCRMBPM.FUNC = 'R';
            S000_CALL_BPZRMBPM();
            if (pgmRtn) return;
            IBS.CPY2CLS(SCCGWA, BPRPARM.KEY.CODE, WS_PARM_KEY);
            if (WS_PARM_KEY.WS_PARM_TX.equalsIgnoreCase(BPCSMACD.TXN_CD) 
                && WS_PARM_KEY.WS_PARM_SEQ != BPCSMACD.SEQ_NO) {
                WS_DISPLAY_SEQ = " ";
                if (BPRPARM.BLOB_VAL == null) BPRPARM.BLOB_VAL = "";
                JIBS_tmp_int = BPRPARM.BLOB_VAL.length();
                for (int i=0;i<9500-JIBS_tmp_int;i++) BPRPARM.BLOB_VAL += " ";
                WS_DISPLAY_SEQ = BPRPARM.BLOB_VAL.substring(119 - 1, 119 + 2 - 1);
                if (WS_DISPLAY_SEQ.equalsIgnoreCase(BPCSMACD.PARM_DATA.DISPLAY_SEQ)) {
                    IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_DIS_SEQ_DUP, WS_RC);
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
        }
        IBS.init(SCCGWA, BPRPARM);
        IBS.init(SCCGWA, BPCRMBPM);
        BPCRMBPM.FUNC = 'E';
        S000_CALL_BPZRMBPM();
        if (pgmRtn) return;
    }
    public void B200_GET_SEQ() throws IOException,SQLException,Exception {
        B230_BROWSE_GET_SEQ();
        if (pgmRtn) return;
        if (BPCSMACD.FUNC == 'G') {
            WS_SEQ = (short) (WS_SEQ + 1);
            IBS.init(SCCGWA, SCCFMT);
            IBS.init(SCCGWA, BPCOMACD.KEY);
            IBS.CPY2CLS(SCCGWA, BPCSMACD.TXN_CD, BPCOMACD.KEY);
            BPCOMACD.KEY.SEQ_NO = WS_SEQ;
            SCCFMT.FMTID = K_FMT_CD_2;
            CEP.TRC(SCCGWA, BPCOMACD.KEY);
            SCCFMT.DATA_PTR = BPCOMACD.KEY;
            SCCFMT.DATA_LEN = 11;
            IBS.FMT(SCCGWA, SCCFMT);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSMACD.SEQ_NO);
        CEP.TRC(SCCGWA, WS_SEQ);
        if (BPCSMACD.FUNC == 'D') {
            if (BPCSMACD.SEQ_NO < WS_SEQ) {
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_DELETE_LAST, WS_RC);
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B110_GET_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSMACD.FUNC);
        IBS.init(SCCGWA, BPRPRMT);
        BPRPRMT.KEY.TYP = K_PARM_CODE_TYPE;
        WS_PARM_KEY.WS_PARM_TX = BPCSMACD.TXN_CD;
        WS_PARM_KEY.WS_PARM_SEQ = BPCSMACD.SEQ_NO;
        BPRPRMT.KEY.CD = IBS.CLS2CPY(SCCGWA, WS_PARM_KEY);
        BPCPRMM.EFF_DT = BPCSMACD.EFF_DATE;
        CEP.TRC(SCCGWA, BPCSMACD.EFF_DATE);
        if (BPCSMACD.FUNC == 'A') {
            BPCSMACD.PARM_DATA.OPEN_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCSMACD.PARM_DATA.LST_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCSMACD.PARM_DATA.LST_TLT = SCCGWA.COMM_AREA.TL_ID;
        }
        if (BPCSMACD.FUNC == 'U') {
            BPCSMACD.PARM_DATA.LST_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCSMACD.PARM_DATA.LST_TLT = SCCGWA.COMM_AREA.TL_ID;
        }
        if (BPCSMACD.EXP_DATE == 0) {
            BPCPRMM.EXP_DT = 99991231;
        } else {
            BPCPRMM.EXP_DT = BPCSMACD.EXP_DATE;
        }
        BPRPRMT.DAT_TXT.FILLER = IBS.CLS2CPY(SCCGWA, BPCSMACD.PARM_DATA);
        CEP.TRC(SCCGWA, BPCPRMM.EXP_DT);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, BPCSMACD.EXP_DATE);
        CEP.TRC(SCCGWA, BPRPRMT.DAT_TXT.FILLER);
    }
    public void B210_KEY_PROCESS() throws IOException,SQLException,Exception {
        B110_GET_INPUT_DATA();
        if (pgmRtn) return;
        if (BPCSMACD.FUNC == 'Q'
            || BPCSMACD.FUNC == 'C') {
            BPCPRMM.FUNC = '3';
        } else if (BPCSMACD.FUNC == 'A') {
            BPCPRMM.FUNC = '0';
        } else if (BPCSMACD.FUNC == 'U'
            || BPCSMACD.FUNC == 'D') {
            BPCPRMM.FUNC = '4';
        } else {
        }
        CEP.TRC(SCCGWA, "CALL BPZPRMM FIRST");
        CEP.TRC(SCCGWA, BPCPRMM.FUNC);
        CEP.TRC(SCCGWA, BPRPRMT.KEY.TYP);
        S000_CALL_BPZPRMM();
        if (pgmRtn) return;
        B300_CHECK_RETURN_1();
        if (pgmRtn) return;
        if (BPCSMACD.FUNC == 'D') {
            BPCPRMM.FUNC = '1';
            S000_CALL_BPZPRMM();
            if (pgmRtn) return;
        }
        if (BPCSMACD.FUNC == 'U') {
            IBS.CLONE(SCCGWA, BPRPRMT, BPRBPRMT);
            IBS.init(SCCGWA, BPCOHACD);
            BPCOHACD.KEY.TXN_CD = BPCSMACD.TXN_CD;
            BPCOHACD.KEY.SEQ_NO = BPCSMACD.SEQ_NO;
