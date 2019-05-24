package com.hisun.BP;

import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class BPZBSP65 {
    int JIBS_tmp_int;
    DBParm BPTBSP65_RD;
    brParm BPTBSP65_BR = new brParm();
    int WS_REC_LEN = 0;
    int WS_KEY_LEN = 0;
    int WS_TMP_NUM = 0;
    BPZBSP65_WS_JRN_NO WS_JRN_NO = new BPZBSP65_WS_JRN_NO();
    BPRBSP65 BPRBSP65 = new BPRBSP65();
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    BPCI1198 BPCI1198 = new BPCI1198();
    BPCRFPDT BPCRFPDT = new BPCRFPDT();
    BPRFPDT BPRFPDT = new BPRFPDT();
    BPCOFBAS BPCOFBAS = new BPCOFBAS();
    SCCGWA SCCGWA;
    SCCRWBSP SCCRWBSP;
    String LS_REC = " ";
    public void MP(SCCGWA SCCGWA, SCCRWBSP SCCRWBSP) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.SCCRWBSP = SCCRWBSP;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "BPZBSP65 return!");
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
        IBS.CPY2CLS(SCCGWA, LS_REC.substring(0, WS_KEY_LEN), BPRBSP65.KEY);
        IBS.CPY2CLS(SCCGWA, BPCMSG_ERROR_MSG.BP_NORMAL, SCCRWBSP.RC);
        SCCRWBSP.RETURN_INFO = 'F';
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
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
        T000_READ_UPDATE_BPTBSP65();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (LS_REC == null) LS_REC = "";
            JIBS_tmp_int = LS_REC.length();
            for (int i=0;i<20480-JIBS_tmp_int;i++) LS_REC += " ";
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRBSP65);
            LS_REC = JIBS_tmp_str[0] + LS_REC.substring(WS_REC_LEN);
        } else {
            SCCRWBSP.RETURN_INFO = 'N';
        }
    }
    public void B200_UPDATE() throws IOException,SQLException,Exception {
        if (LS_REC == null) LS_REC = "";
        JIBS_tmp_int = LS_REC.length();
        for (int i=0;i<20480-JIBS_tmp_int;i++) LS_REC += " ";
        IBS.CPY2CLS(SCCGWA, LS_REC.substring(0, WS_REC_LEN), BPRBSP65);
        T000_REWRITE_BPTBSP65();
        if (BPRBSP65.STATUS == 'E') {
            IBS.init(SCCGWA, BPCRFPDT);
            IBS.init(SCCGWA, BPRFPDT);
            IBS.init(SCCGWA, BPCI1198);
            IBS.CPY2CLS(SCCGWA, BPRBSP65.BLOB_TR_DATA, BPCI1198);
            R000_GET_FBAS_INFO();
            if (BPCOFBAS.VAL.DEBT_METH != '3') {
                R000_WRITE_FPDT_INFO();
            }
        }
    }
    public void R000_GET_FBAS_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOFBAS);
        BPCOFBAS.KEY.FEE_CODE = BPCI1198.FEE_CODE;
        BPCOFBAS.FUNC = 'I';
        IBS.CALLCPN(SCCGWA, "BP-F-U-MAINTAIN-FBAS", BPCOFBAS);
    }
    public void R000_WRITE_FPDT_INFO() throws IOException,SQLException,Exception {
        BPRFPDT.KEY.TRT_DT = BPRBSP65.AC_DATE;
        BPRFPDT.KEY.JRN_NO = "" + SCCGWA.COMM_AREA.JRN_NO;
        JIBS_tmp_int = BPRFPDT.KEY.JRN_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BPRFPDT.KEY.JRN_NO = "0" + BPRFPDT.KEY.JRN_NO;
        BPRFPDT.KEY.JRN_SEQ = 1;
        BPRFPDT.ACC_RECH_CNT = 0;
        BPRFPDT.CHG_STS = '0';
        BPRFPDT.ACC_CHG_AMT = 0;
        BPRFPDT.FEE_SRC = '2';
        BPRFPDT.PRC_STS = '0';
        BPRFPDT.CARD_PSBK_NO = BPCI1198.C_P_NO;
        BPRFPDT.TX_CI = BPCI1198.CI_NO;
        BPRFPDT.CHG_AC_TY = BPCI1198.AC_TYP;
        if (BPCI1198.AC_TYP == '4' 
            || BPCI1198.AC_TYP == '5') {
            BPRFPDT.CHG_AC = BPCI1198.C_P_NO;
        } else {
            BPRFPDT.CHG_AC = BPCI1198.FEE_AC;
        }
        BPRFPDT.CHG_BR = BPCI1198.BR;
        BPRFPDT.TRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRFPDT.CCY = BPCI1198.CHG_CCY;
        BPRFPDT.CCY_TYPE = BPCI1198.CCY_TYPE;
        BPRFPDT.FEE_CODE = BPCI1198.FEE_CODE;
        BPRFPDT.CHG_AMT = BPCI1198.CHG_AMT;
        BPRFPDT.CUR_OWE_AMT = BPCI1198.CHG_AMT;
        BPRFPDT.CREATE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRFPDT.UPDATE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRFPDT.LAST_TELL = SCCGWA.COMM_AREA.TL_ID;
        BPRFPDT.PRD_CD = BPCI1198.PRD_CODE;
        BPRFPDT.REMARK = "BSP65";
        BPCRFPDT.INFO.FUNC = 'C';
        BPCRFPDT.INFO.POINTER = BPRFPDT;
        BPCRFPDT.INFO.LEN = 558;
        IBS.CALLCPN(SCCGWA, "BP-F-R-FE-UNCHG-DTL", BPCRFPDT);
    }
    public void B300_STARTBR() throws IOException,SQLException,Exception {
        T000_STARTBR_BPTBSP65();
    }
    public void B400_READNEXT() throws IOException,SQLException,Exception {
        T000_READNEXT_BPTBSP65();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (LS_REC == null) LS_REC = "";
            JIBS_tmp_int = LS_REC.length();
            for (int i=0;i<20480-JIBS_tmp_int;i++) LS_REC += " ";
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRBSP65);
            LS_REC = JIBS_tmp_str[0] + LS_REC.substring(WS_REC_LEN);
        } else {
            SCCRWBSP.RETURN_INFO = 'N';
        }
    }
    public void B500_ENDBR() throws IOException,SQLException,Exception {
        T000_ENDBR_BPTBSP65();
    }
    public void B600_INQURE() throws IOException,SQLException,Exception {
        T000_READ_BPTBSP65();
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            if (LS_REC == null) LS_REC = "";
            JIBS_tmp_int = LS_REC.length();
            for (int i=0;i<20480-JIBS_tmp_int;i++) LS_REC += " ";
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPRBSP65);
            LS_REC = JIBS_tmp_str[0] + LS_REC.substring(WS_REC_LEN);
        } else {
            SCCRWBSP.RETURN_INFO = 'N';
        }
    }
    public void B700_CREATE() throws IOException,SQLException,Exception {
        if (LS_REC == null) LS_REC = "";
        JIBS_tmp_int = LS_REC.length();
        for (int i=0;i<20480-JIBS_tmp_int;i++) LS_REC += " ";
        IBS.CPY2CLS(SCCGWA, LS_REC.substring(0, WS_REC_LEN), BPRBSP65);
        T000_WRITE_BPTBSP65();
    }
    public void B800_STARTBR1() throws IOException,SQLException,Exception {
        T000_STARTBR1_BPTBSP65();
    }
    public void T000_READ_BPTBSP65() throws IOException,SQLException,Exception {
        BPTBSP65_RD = new DBParm();
        BPTBSP65_RD.TableName = "BPTBSP65";
        IBS.READ(SCCGWA, BPRBSP65, BPTBSP65_RD);
    }
    public void T000_READ_UPDATE_BPTBSP65() throws IOException,SQLException,Exception {
        BPTBSP65_RD = new DBParm();
        BPTBSP65_RD.TableName = "BPTBSP65";
        BPTBSP65_RD.col = "STATUS,RT_CODE,OUT_JRN,OUT_VCH_NO";
        BPTBSP65_RD.upd = true;
        IBS.READ(SCCGWA, BPRBSP65, BPTBSP65_RD);
    }
    public void T000_REWRITE_BPTBSP65() throws IOException,SQLException,Exception {
        BPTBSP65_RD = new DBParm();
        BPTBSP65_RD.TableName = "BPTBSP65";
        IBS.REWRITE(SCCGWA, BPRBSP65, BPTBSP65_RD);
    }
    public void T000_STARTBR_BPTBSP65() throws IOException,SQLException,Exception {
        BPTBSP65_BR.rp = new DBParm();
        BPTBSP65_BR.rp.TableName = "BPTBSP65";
        BPTBSP65_BR.rp.order = "AP_TYPE,AP_BATNO,BAT_NO";
        IBS.STARTBR(SCCGWA, BPRBSP65, BPTBSP65_BR);
    }
    public void T000_STARTBR1_BPTBSP65() throws IOException,SQLException,Exception {
        BPTBSP65_BR.rp = new DBParm();
        BPTBSP65_BR.rp.TableName = "BPTBSP65";
        BPTBSP65_BR.rp.where = "( AP_TYPE = :BPRBSP65.KEY.AP_TYPE "
            + "AND AP_BATNO = :BPRBSP65.KEY.AP_BATNO "
            + "AND BAT_NO > :BPRBSP65.KEY.BAT_NO ) "
            + "OR ( AP_TYPE = :BPRBSP65.KEY.AP_TYPE "
            + "AND AP_BATNO > :BPRBSP65.KEY.AP_BATNO ) "
            + "OR ( AP_TYPE > :BPRBSP65.KEY.AP_TYPE )";
        BPTBSP65_BR.rp.order = "AP_TYPE,AP_BATNO,BAT_NO";
        IBS.STARTBR(SCCGWA, BPRBSP65, this, BPTBSP65_BR);
    }
    public void T000_READNEXT_BPTBSP65() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRBSP65, this, BPTBSP65_BR);
    }
    public void T000_ENDBR_BPTBSP65() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTBSP65_BR);
    }
    public void T000_WRITE_BPTBSP65() throws IOException,SQLException,Exception {
        BPTBSP65_RD = new DBParm();
        BPTBSP65_RD.TableName = "BPTBSP65";
        IBS.WRITE(SCCGWA, BPRBSP65, BPTBSP65_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            SCCRWBSP.RETURN_INFO = 'F';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            SCCRWBSP.RETURN_INFO = 'D';
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "BPTBSP65";
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
