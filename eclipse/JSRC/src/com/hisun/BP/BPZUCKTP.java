package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZUCKTP {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_BP_RGND_GROUP = "BP-R-RGND-GROUP ";
    String CPN_R_BRW_RGND = "BP-R-BRW-RGND ";
    BPZUCKTP_WS_TEMP_CODE WS_TEMP_CODE = new BPZUCKTP_WS_TEMP_CODE();
    BPZUCKTP_WS_RGND_DETAIL WS_RGND_DETAIL = new BPZUCKTP_WS_RGND_DETAIL();
    int WS_CNT = 0;
    char WS_COND_FLG = ' ';
    short WS_BR_LENG = 0;
    String WS_ERR_MSG = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRPARM BPRPARM = new BPRPARM();
    BPRRGND BPRRGND = new BPRRGND();
    BPCTBRGN BPCTBRGN = new BPCTBRGN();
    BPCRRGDG BPCRRGDG = new BPCRRGDG();
    BPCRMBPM BPCRMBPM = new BPCRMBPM();
    BPRPRGCM BPRPRGCM = new BPRPRGCM();
    SCCGWA SCCGWA;
    BPCRCKPM BPCRCKPM;
    public void MP(SCCGWA SCCGWA, BPCRCKPM BPCRCKPM) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCRCKPM = BPCRCKPM;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZUCKTP return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        WS_COND_FLG = ' ';
        IBS.init(SCCGWA, BPCTBRGN);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_GET_CODE_INFO();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_GET_CODE_INFO() throws IOException,SQLException,Exception {
        WS_BR_LENG = 6;
        IBS.CPY2CLS(SCCGWA, BPCRCKPM.CODE, WS_TEMP_CODE);
        WS_COND_FLG = BPCRCKPM.VAL.charAt(0);
        CEP.TRC(SCCGWA, BPCRCKPM.CODE);
        CEP.TRC(SCCGWA, WS_COND_FLG);
        CEP.TRC(SCCGWA, WS_TEMP_CODE.WS_BANK_CODE);
        CEP.TRC(SCCGWA, WS_TEMP_CODE.WS_RGN_TYPE);
        if (WS_COND_FLG == 'N') {
            IBS.init(SCCGWA, BPRRGND);
            BPRRGND.KEY.BNK = WS_TEMP_CODE.WS_BANK_CODE;
            BPRRGND.KEY.RGN_TYPE = WS_TEMP_CODE.WS_RGN_TYPE;
            BPCTBRGN.FUNC = 'S';
            BPCTBRGN.INFO.POINTER = BPRRGND;
            BPCTBRGN.DATA_LEN = 184;
            S000_CALL_BPZTBRGN();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, "AFTER STARTBR");
            BPCTBRGN.FUNC = 'R';
            CEP.TRC(SCCGWA, "AFTER SET");
            BPCTBRGN.INFO.POINTER = BPRRGND;
            BPCTBRGN.DATA_LEN = 184;
            CEP.TRC(SCCGWA, BPRRGND);
            S000_CALL_BPZTBRGN();
            if (pgmRtn) return;
            IBS.init(SCCGWA, BPCRRGDG);
            for (WS_CNT = 1; BPCRRGDG.RETURN_CNT <= 1 
                && BPCTBRGN.RETURN_INFO != 'N'; WS_CNT += 1) {
                IBS.init(SCCGWA, BPCRRGDG);
                BPCRRGDG.INFO.FUNC = 'G';
                BPCRRGDG.INFO.RGN_TYPE = BPRRGND.KEY.RGN_TYPE;
                BPCRRGDG.INFO.BNK = BPRRGND.KEY.BNK;
                if (BPRRGND.KEY.RGN_UNIT == null) BPRRGND.KEY.RGN_UNIT = "";
                JIBS_tmp_int = BPRRGND.KEY.RGN_UNIT.length();
                for (int i=0;i<20-JIBS_tmp_int;i++) BPRRGND.KEY.RGN_UNIT += " ";
                if (BPRRGND.KEY.RGN_UNIT.substring(0, WS_BR_LENG).trim().length() == 0) BPCRRGDG.INFO.BR = 0;
                else BPCRRGDG.INFO.BR = Integer.parseInt(BPRRGND.KEY.RGN_UNIT.substring(0, WS_BR_LENG));
                S000_CALL_BPZTRGDG();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPCRRGDG.RETURN_CNT);
                CEP.TRC(SCCGWA, BPCRRGDG.INFO.BR);
                CEP.TRC(SCCGWA, BPCRRGDG.INFO.RGN_TYPE);
                CEP.TRC(SCCGWA, BPCRRGDG.INFO.BNK);
                BPCTBRGN.FUNC = 'R';
                BPCTBRGN.INFO.POINTER = BPRRGND;
                BPCTBRGN.DATA_LEN = 184;
                S000_CALL_BPZTBRGN();
                if (pgmRtn) return;
            }
            if (BPCRRGDG.RETURN_CNT > 1) {
                CEP.TRC(SCCGWA, "AFTER SET");
                IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_SAME_BR_PROHIBITED, BPCRCKPM.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            BPCTBRGN.FUNC = 'E';
            BPCTBRGN.INFO.POINTER = BPRRGND;
            BPCTBRGN.DATA_LEN = 184;
            S000_CALL_BPZTBRGN();
            if (pgmRtn) return;
        }
        if (BPCRCKPM.FUNC == 'D') {
            IBS.init(SCCGWA, BPRPARM);
            IBS.init(SCCGWA, BPRPRGCM);
            IBS.init(SCCGWA, BPCRMBPM);
            BPCRMBPM.FUNC = 'S';
            BPRPARM.KEY.TYPE = "RGNCD";
            BPCRMBPM.PTR = BPRPARM;
            S000_CALL_BPZRMBPM();
            if (pgmRtn) return;
            if (BPRPRGCM.KEY.CD == null) BPRPRGCM.KEY.CD = "";
            JIBS_tmp_int = BPRPRGCM.KEY.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRGCM.KEY.CD += " ";
            while (BPCRMBPM.RETURN_INFO != 'L' 
                && BPRPRGCM.KEY.CD.substring(4 - 1, 4 + 2 - !1).equalsIgnoreCase(BPCRCKPM.CODE)) {
                BPCRMBPM.FUNC = 'R';
                S000_CALL_BPZRMBPM();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPRPARM.KEY.CODE);
                BPRPRGCM.KEY.CD = BPRPARM.KEY.CODE;
                CEP.TRC(SCCGWA, BPRPRGCM.KEY.CD);
                if (BPRPRGCM.KEY.CD == null) BPRPRGCM.KEY.CD = "";
                JIBS_tmp_int = BPRPRGCM.KEY.CD.length();
                for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRGCM.KEY.CD += " ";
                CEP.TRC(SCCGWA, BPRPRGCM.KEY.CD.substring(4 - 1, 4 + 2 - 1));
                CEP.TRC(SCCGWA, BPCRCKPM.CODE);
            }
            CEP.TRC(SCCGWA, BPRPRGCM.KEY.CD);
            if (BPRPRGCM.KEY.CD == null) BPRPRGCM.KEY.CD = "";
            JIBS_tmp_int = BPRPRGCM.KEY.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRGCM.KEY.CD += " ";
            CEP.TRC(SCCGWA, BPRPRGCM.KEY.CD.substring(4 - 1, 4 + 2 - 1));
            if (BPRPRGCM.KEY.CD == null) BPRPRGCM.KEY.CD = "";
            JIBS_tmp_int = BPRPRGCM.KEY.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPRPRGCM.KEY.CD += " ";
            if (BPCRMBPM.RETURN_INFO == 'F' 
                && BPRPRGCM.KEY.CD.substring(4 - 1, 4 + 2 - 1).equalsIgnoreCase(BPCRCKPM.CODE)) {
                CEP.TRC(SCCGWA, "20140703");
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RGN_SEQ_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            BPCRMBPM.FUNC = 'E';
            S000_CALL_BPZRMBPM();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTBRGN() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_BRW_RGND, BPCTBRGN);
        CEP.TRC(SCCGWA, BPCTBRGN.RC.RC_CODE);
        if (BPCTBRGN.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCTBRGN.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], BPCRCKPM.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZTRGDG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_RGND_GROUP, BPCRRGDG);
    }
    public void S000_CALL_BPZRMBPM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-R-MBRW-PARM", BPCRMBPM);
        CEP.TRC(SCCGWA, "20140704");
        CEP.TRC(SCCGWA, BPCRMBPM.RC.RC_CODE);
        if (BPCRMBPM.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCRMBPM.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCRCKPM.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "BPCRCKPM = ");
            CEP.TRC(SCCGWA, BPCRCKPM);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
