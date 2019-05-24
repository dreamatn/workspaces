package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZBPCD {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_PGM_NAME = "BPZBPCD";
    String K_PARM_TYP = "PARMC";
    String CPN_BP_PARM_BPZPRMB = "BP-PARM-BROWSE      ";
    String WS_ERR_MSG = " ";
    int WS_EFF_DT = 0;
    int WS_EXP_DT = 0;
    short WS_CNT = 0;
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCGSCA_SC_AREA GWA_SC_AREA = new SCCGSCA_SC_AREA();
    SCCGBPA_BP_AREA GWA_BP_AREA = new SCCGBPA_BP_AREA();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPRPRMT BPRPPCD = new BPRPRMT();
    BPCPRMB BPCPRMB = new BPCPRMB();
    SCCGWA SCCGWA;
    BPCBPCD BPCBPCD;
    public void MP(SCCGWA SCCGWA, BPCBPCD BPCBPCD) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCBPCD = BPCBPCD;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZBPCD return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPPCD);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (BPCBPCD.INPUT_DAT.FUNC == 'B') {
            B020_STARTBR_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCBPCD.INPUT_DAT.FUNC == 'N') {
            B030_READNEXT_RECORD_PROC();
            if (pgmRtn) return;
        } else if (BPCBPCD.INPUT_DAT.FUNC == 'E') {
            B040_ENDBR_RECORD_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_INPUT_ERROR, BPCBPCD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (BPCBPCD.INPUT_DAT.TYPE.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PCD_TYP_MUST_INPUT, BPCBPCD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B020_STARTBR_RECORD_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRPPCD);
        IBS.init(SCCGWA, BPCPRMB);
        BPRPPCD.KEY.TYP = K_PARM_TYP;
        if (BPRPPCD.KEY.CD == null) BPRPPCD.KEY.CD = "";
        JIBS_tmp_int = BPRPPCD.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPPCD.KEY.CD += " ";
        if (BPCBPCD.INPUT_DAT.TYPE == null) BPCBPCD.INPUT_DAT.TYPE = "";
        JIBS_tmp_int = BPCBPCD.INPUT_DAT.TYPE.length();
        for (int i=0;i<5-JIBS_tmp_int;i++) BPCBPCD.INPUT_DAT.TYPE += " ";
        BPRPPCD.KEY.CD = BPCBPCD.INPUT_DAT.TYPE + BPRPPCD.KEY.CD.substring(5);
        BPRPPCD.DAT_LEN = 425;
        BPRPPCD.DAT_TXT.FILLER = IBS.CLS2CPY(SCCGWA, BPCBPCD.DATA);
        BPCPRMB.DAT_PTR = BPRPPCD;
        BPCPRMB.FUNC = '0';
        CEP.TRC(SCCGWA, BPRPPCD.KEY.TYP);
        CEP.TRC(SCCGWA, BPRPPCD.KEY.CD);
        CEP.TRC(SCCGWA, BPCPRMB.FUNC);
        CEP.TRC(SCCGWA, BPCPRMB.EFF_DT);
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPRMB.RC);
    }
    public void B030_READNEXT_RECORD_PROC() throws IOException,SQLException,Exception {
        BPCPRMB.FUNC = '1';
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
        S000_TRANS_DATA_OUT();
        if (pgmRtn) return;
    }
    public void B040_ENDBR_RECORD_PROC() throws IOException,SQLException,Exception {
        BPCPRMB.FUNC = '2';
        S000_CALL_BPZPRMB();
        if (pgmRtn) return;
    }
    public void S000_TRANS_DATA_OUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRPPCD.DAT_TXT.FILLER);
        CEP.TRC(SCCGWA, BPRPPCD.KEY.CD);
        if (BPRPPCD.KEY.CD == null) BPRPPCD.KEY.CD = "";
        JIBS_tmp_int = BPRPPCD.KEY.CD.length();
        for (int i=0;i<40-JIBS_tmp_int;i++) BPRPPCD.KEY.CD += " ";
        if (BPCBPCD.INPUT_DAT.TYPE.equalsIgnoreCase(BPRPPCD.KEY.CD.substring(0, 5))) {
            if (BPRPPCD.KEY.CD == null) BPRPPCD.KEY.CD = "";
            JIBS_tmp_int = BPRPPCD.KEY.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPRPPCD.KEY.CD += " ";
            BPCBPCD.INPUT_DAT.TYPE = BPRPPCD.KEY.CD.substring(0, 5);
            if (BPRPPCD.KEY.CD == null) BPRPPCD.KEY.CD = "";
            JIBS_tmp_int = BPRPPCD.KEY.CD.length();
            for (int i=0;i<40-JIBS_tmp_int;i++) BPRPPCD.KEY.CD += " ";
            BPCBPCD.OUTPUT_DAT.CODE = BPRPPCD.KEY.CD.substring(6 - 1, 6 + 10 - 1);
            BPCBPCD.OUTPUT_DAT.DESC = BPRPPCD.DESC;
            BPCBPCD.OUTPUT_DAT.CDESC = BPRPPCD.CDESC;
            IBS.CPY2CLS(SCCGWA, BPRPPCD.DAT_TXT.FILLER, BPCBPCD.DATA);
        } else {
            BPCBPCD.OUTPUT_DAT.RTN_INF = 'N';
        }
        CEP.TRC(SCCGWA, BPCBPCD.OUTPUT_DAT.CODE);
        CEP.TRC(SCCGWA, BPCBPCD.OUTPUT_DAT.DESC);
        CEP.TRC(SCCGWA, BPCBPCD.OUTPUT_DAT.CDESC);
        CEP.TRC(SCCGWA, BPCBPCD.DATA);
    }
    public void S000_CALL_BPZPRMB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_BP_PARM_BPZPRMB, BPCPRMB);
        R000_CHECK_RETURNCODE_BROWSE();
        if (pgmRtn) return;
    }
    public void R000_CHECK_RETURNCODE_BROWSE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCPRMB.RC);
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCBPCD.INPUT_DAT.TYPE);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
            BPCBPCD.RC.RTNCODE = 0;
            BPCBPCD.OUTPUT_DAT.RTN_INF = 'Y';
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCBPCD.INPUT_DAT.TYPE);
        } else if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_PARM_NOTFND)) {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_PARM_NOTFND, BPCBPCD.RC);
            BPCBPCD.OUTPUT_DAT.RTN_INF = 'N';
        } else {
            IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_TABLE_ACC_ERR, BPCBPCD.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (BPCBPCD.RC.RTNCODE != 0) {
            CEP.TRC(SCCGWA, "BPCBPCD = ");
            CEP.TRC(SCCGWA, BPCBPCD);
        }
    } //FROM #ENDIF
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
