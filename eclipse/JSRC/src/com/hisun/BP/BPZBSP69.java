package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZBSP69 {
    int JIBS_tmp_int;
    DBParm BPTBSP69_RD;
    brParm BPTBSP69_BR = new brParm();
    int WS_REC_LEN = 0;
    int WS_KEY_LEN = 0;
    int WS_TMP_NUM = 0;
    BPRBSP69 BPRBSP69 = new BPRBSP69();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCGWA SCCGWA;
    SCCRWBSP SCCRWBSP;
    String LS_REC = " ";
    public void MP(SCCGWA SCCGWA, SCCRWBSP SCCRWBSP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SCCRWBSP = SCCRWBSP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZBSP69 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        WS_REC_LEN = 236;
        WS_KEY_LEN = 30;
        LS_REC = IBS.CLS2CPY(SCCGWA, SCCRWBSP.INFO.PTR);
        SCCRWBSP.INFO.LEN = 236;
        if (LS_REC == null) LS_REC = "";
        JIBS_tmp_int = LS_REC.length();
        for (int i=0;i<20480-JIBS_tmp_int;i++) LS_REC += " ";
        IBS.CPY2CLS(SCCGWA, LS_REC.substring(0, WS_KEY_LEN), BPRBSP69.KEY);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, SCCRWBSP.RC);
        SCCRWBSP.RETURN_INFO = 'F';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPRBSP69.KEY.AP_TYPE);
        CEP.TRC(SCCGWA, BPRBSP69.KEY.AP_BATNO);
        CEP.TRC(SCCGWA, BPRBSP69.KEY.BAT_NO);
        CEP.TRC(SCCGWA, SCCRWBSP.INFO.FUNC);
        if (SCCRWBSP.INFO.FUNC == 'R') {
            B100_READUPD();
        } else if (SCCRWBSP.INFO.FUNC == 'U') {
            B200_UPDATE();
        } else if (SCCRWBSP.INFO.FUNC == 'S') {
            B300_STARTBR();
        } else if (SCCRWBSP.INFO.FUNC == 'N') {
            B400_READNEXT();
        } else if (SCCRWBSP.INFO.FUNC == 'E') {
            B500_ENDBR();
        } else if (SCCRWBSP.INFO.FUNC == 'Q') {
            B600_INQURE();
        } else if (SCCRWBSP.INFO.FUNC == 'C') {
            B700_CREATE();
        } else if (SCCRWBSP.INFO.FUNC == 'T') {
            B800_STARTBR1();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + SCCRWBSP.INFO.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void B100_READUPD() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BPREADUP");
        T000_READ_UPDATE_BPTBSP69();
        CEP.TRC(SCCGWA, BPRBSP69);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (LS_REC == null) LS_REC = "";
            JIBS_tmp_int = LS_REC.length();
            for (int i=0;i<20480-JIBS_tmp_int;i++) LS_REC += " ";
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRBSP69);
            LS_REC = JIBS_tmp_str[0] + LS_REC.substring(WS_REC_LEN);
        } else {
            SCCRWBSP.RETURN_INFO = 'N';
        }
    }
    public void B200_UPDATE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "BPREWRITE");
        if (LS_REC == null) LS_REC = "";
        JIBS_tmp_int = LS_REC.length();
        for (int i=0;i<20480-JIBS_tmp_int;i++) LS_REC += " ";
        IBS.CPY2CLS(SCCGWA, LS_REC.substring(0, WS_REC_LEN), BPRBSP69);
        T000_REWRITE_BPTBSP69();
    }
    public void B300_STARTBR() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTBSP69();
    }
    public void B400_READNEXT() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTBSP69();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (LS_REC == null) LS_REC = "";
            JIBS_tmp_int = LS_REC.length();
            for (int i=0;i<20480-JIBS_tmp_int;i++) LS_REC += " ";
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRBSP69);
            LS_REC = JIBS_tmp_str[0] + LS_REC.substring(WS_REC_LEN);
        } else {
            SCCRWBSP.RETURN_INFO = 'N';
        }
    }
    public void B500_ENDBR() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTBSP69();
    }
    public void B600_INQURE() throws IOException,SQLException,Exception {
        T000_READ_BPTBSP69();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (LS_REC == null) LS_REC = "";
            JIBS_tmp_int = LS_REC.length();
            for (int i=0;i<20480-JIBS_tmp_int;i++) LS_REC += " ";
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRBSP69);
            LS_REC = JIBS_tmp_str[0] + LS_REC.substring(WS_REC_LEN);
        } else {
            SCCRWBSP.RETURN_INFO = 'N';
        }
    }
    public void B700_CREATE() throws IOException,SQLException,Exception {
        if (LS_REC == null) LS_REC = "";
        JIBS_tmp_int = LS_REC.length();
        for (int i=0;i<20480-JIBS_tmp_int;i++) LS_REC += " ";
        IBS.CPY2CLS(SCCGWA, LS_REC.substring(0, WS_REC_LEN), BPRBSP69);
        T000_WRITE_BPTBSP69();
    }
    public void B800_STARTBR1() throws IOException,SQLException,Exception {
        T000_STARTBR1_BPTBSP69();
    }
    public void T000_READ_BPTBSP69() throws IOException,SQLException,Exception {
        BPTBSP69_RD = new DBParm();
        BPTBSP69_RD.TableName = "BPTBSP69";
        IBS.READ(SCCGWA, BPRBSP69, BPTBSP69_RD);
    }
    public void T000_READ_UPDATE_BPTBSP69() throws IOException,SQLException,Exception {
        BPTBSP69_RD = new DBParm();
        BPTBSP69_RD.TableName = "BPTBSP69";
        BPTBSP69_RD.col = "STATUS,RT_CODE,OUT_JRN,OUT_VCH_NO";
        BPTBSP69_RD.upd = true;
        IBS.READ(SCCGWA, BPRBSP69, BPTBSP69_RD);
    }
    public void T000_REWRITE_BPTBSP69() throws IOException,SQLException,Exception {
        BPTBSP69_RD = new DBParm();
        BPTBSP69_RD.TableName = "BPTBSP69";
        IBS.REWRITE(SCCGWA, BPRBSP69, BPTBSP69_RD);
    }
    public void T000_STARTBR_BPTBSP69() throws IOException,SQLException,Exception {
        BPTBSP69_BR.rp = new DBParm();
        BPTBSP69_BR.rp.TableName = "BPTBSP69";
        BPTBSP69_BR.rp.order = "AP_TYPE,AP_BATNO,BAT_NO";
        IBS.STARTBR(SCCGWA, BPRBSP69, BPTBSP69_BR);
    }
    public void T000_STARTBR1_BPTBSP69() throws IOException,SQLException,Exception {
        BPTBSP69_BR.rp = new DBParm();
        BPTBSP69_BR.rp.TableName = "BPTBSP69";
        BPTBSP69_BR.rp.where = "( AP_TYPE = :BPRBSP69.KEY.AP_TYPE "
            + "AND AP_BATNO = :BPRBSP69.KEY.AP_BATNO "
            + "AND BAT_NO > :BPRBSP69.KEY.BAT_NO ) "
            + "OR ( AP_TYPE = :BPRBSP69.KEY.AP_TYPE "
            + "AND AP_BATNO > :BPRBSP69.KEY.AP_BATNO ) "
            + "OR ( AP_TYPE > :BPRBSP69.KEY.AP_TYPE )";
        BPTBSP69_BR.rp.order = "AP_TYPE,AP_BATNO,BAT_NO";
        IBS.STARTBR(SCCGWA, BPRBSP69, this, BPTBSP69_BR);
    }
    public void T000_READNEXT_BPTBSP69() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRBSP69, this, BPTBSP69_BR);
    }
    public void T000_ENDBR_BPTBSP69() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTBSP69_BR);
    }
    public void T000_WRITE_BPTBSP69() throws IOException,SQLException,Exception {
        BPTBSP69_RD = new DBParm();
        BPTBSP69_RD.TableName = "BPTBSP69";
        IBS.WRITE(SCCGWA, BPRBSP69, BPTBSP69_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            SCCRWBSP.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTBSP69";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "WRITE";
            B_DB_EXCP();
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
    if (SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") || SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF BAT
        if (SCCRWBSP.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, "SCCRWBSP = ");
            CEP.TRC(SCCGWA, SCCRWBSP);
        }
    } //FROM #ENDIF
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
