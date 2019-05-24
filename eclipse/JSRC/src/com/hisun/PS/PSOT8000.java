package com.hisun.PS;

import com.hisun.SC.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class PSOT8000 {
    int JIBS_tmp_int;
    String JIBS_tmp_str[] = new String[10];
    brParm PSTRGSA_BR = new brParm();
    boolean pgmRtn = false;
    String OUTPUT_FMT = "PS800";
    String P_PSZBRQRY = "PS-P-BR-QRY-PROC";
    PSOT8000_WS_VARIABLES WS_VARIABLES = new PSOT8000_WS_VARIABLES();
    PSOT8000_WS_OUT_PS800 WS_OUT_PS800 = new PSOT8000_WS_OUT_PS800();
    PSCMSG_ERROR_MSG ERROR_MSG = new PSCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCFMT SCCFMT = new SCCFMT();
    PSCBRQRY PSCBRQRY = new PSCBRQRY();
    PSRSSTA PSRSSTA = new PSRSSTA();
    PSRPBKA PSRPBKA = new PSRPBKA();
    PSRMBKA PSRMBKA = new PSRMBKA();
    PSRRGSA PSRRGSA = new PSRRGSA();
    PSRRGSA_WS_DB2_VARIABLES WS_DB2_VARIABLES = new PSRRGSA_WS_DB2_VARIABLES();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA BP_AREA;
    SCCGSCA_SC_AREA SC_AREA;
    PSCI8000 PSCI8000;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROCESS();
        if (pgmRtn) return;
        B000_MAIN_PROCESS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "PSOT8000 return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "PSOT8000 START");
        BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        PSCI8000 = new PSCI8000();
        IBS.init(SCCGWA, PSCI8000);
        IBS.CPY2CLS(SCCGWA, SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, PSCI8000);
        IBS.init(SCCGWA, WS_VARIABLES);
        IBS.init(SCCGWA, WS_OUT_PS800);
    }
    public void B000_MAIN_PROCESS() throws IOException,SQLException,Exception {
        B100_READ_QRY_PROC();
        if (pgmRtn) return;
        B200_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B300_BUSI_LOGIC_CHECK();
        if (pgmRtn) return;
    }
    public void B100_READ_QRY_PROC() throws IOException,SQLException,Exception {
        PSCI8000.RGN_CD = "6667";
        PSCI8000.RGN_SEQ = "01";
    }
    public void B200_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (PSCI8000.RGN_CD.trim().length() == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.PS_RGN_CD_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (PSCI8000.RGN_SEQ.trim().length() == 0) {
            WS_VARIABLES.ERR_MSG = ERROR_MSG.PS_RGN_SEQ_MST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B300_BUSI_LOGIC_CHECK() throws IOException,SQLException,Exception {
        WS_VARIABLES.RGSA_CNT = 0;
        IBS.init(SCCGWA, PSRRGSA);
        WS_VARIABLES.RGSA_FLG = ' ';
        T000_STARTBR_RGSA();
        if (pgmRtn) return;
        T000_READNEXT_RGSA();
        if (pgmRtn) return;
        while (WS_VARIABLES.RGSA_FLG != 'N') {
            R000_OUTPUT_DETAIL();
            if (pgmRtn) return;
            if (WS_VARIABLES.MO_YE_CNT >= 0 
                && WS_VARIABLES.MO_NO_CNT == 0) {
                CEP.TRC(SCCGWA, "SS");
                T999_A();
                if (pgmRtn) return;
            }
            T000_READNEXT_RGSA();
            if (pgmRtn) return;
        }
        T000_ENDBR_RGSA();
        if (pgmRtn) return;
    }
    public void R000_OUTPUT_DETAIL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "88");
        WS_VARIABLES.MO_NO_CNT = 0;
        WS_VARIABLES.MO_YE_CNT = 0;
        if (PSCI8000.IO_FLG == ' ') {
            if (PSCI8000.IO_FLG == PSRRGSA.IO_FLG) {
                WS_VARIABLES.MO_YE_CNT += 1;
            } else {
                WS_VARIABLES.MO_NO_CNT += 1;
            }
        }
        if (PSCI8000.PAY_STS.trim().length() == 0) {
            if (PSCI8000.PAY_STS.equalsIgnoreCase(PSRRGSA.PAY_STS)) {
                WS_VARIABLES.MO_YE_CNT += 1;
            } else {
                WS_VARIABLES.MO_NO_CNT += 1;
            }
        }
        if (PSCI8000.R_TMS == 0) {
            if (PSCI8000.R_TMS == PSRRGSA.R_TMS) {
                WS_VARIABLES.MO_YE_CNT += 1;
            } else {
                WS_VARIABLES.MO_NO_CNT += 1;
            }
        }
        if (PSCI8000.EX_DT == 0) {
            if (PSCI8000.EX_DT == PSRRGSA.EX_DT) {
                WS_VARIABLES.MO_YE_CNT += 1;
            } else {
                WS_VARIABLES.MO_NO_CNT += 1;
            }
        }
        if (PSCI8000.EX_TMS == 0) {
            if (PSCI8000.EX_TMS == PSRRGSA.EX_TMS) {
                WS_VARIABLES.MO_YE_CNT += 1;
            } else {
                WS_VARIABLES.MO_NO_CNT += 1;
            }
        }
        if (PSCI8000.CCY.trim().length() == 0) {
            if (PSCI8000.CCY.equalsIgnoreCase(PSRRGSA.KEY.CCY)) {
                WS_VARIABLES.MO_YE_CNT += 1;
            } else {
                WS_VARIABLES.MO_NO_CNT += 1;
            }
        }
        if (PSCI8000.CASH_ID == ' ') {
            if (PSCI8000.CASH_ID == PSRRGSA.CASH_ID) {
                WS_VARIABLES.MO_YE_CNT += 1;
            } else {
                WS_VARIABLES.MO_NO_CNT += 1;
            }
        }
        if (PSCI8000.SND_BR.trim().length() == 0) {
            if (PSCI8000.SND_BR.equalsIgnoreCase(PSRRGSA.SND_BR+"")) {
                WS_VARIABLES.MO_YE_CNT += 1;
            } else {
                WS_VARIABLES.MO_NO_CNT += 1;
            }
        }
        if (PSCI8000.REC_BR.trim().length() == 0) {
            if (PSCI8000.REC_BR.equalsIgnoreCase(PSRRGSA.REC_BR+"")) {
                WS_VARIABLES.MO_YE_CNT += 1;
            } else {
                WS_VARIABLES.MO_NO_CNT += 1;
            }
        }
        if (PSCI8000.AMT == 0) {
            if (PSCI8000.AMT == PSRRGSA.AMT) {
                WS_VARIABLES.MO_YE_CNT += 1;
            } else {
                WS_VARIABLES.MO_NO_CNT += 1;
            }
        }
        if (PSCI8000.TX_BR == 0) {
            if (PSCI8000.TX_BR == PSRRGSA.TX_BR) {
                WS_VARIABLES.MO_YE_CNT += 1;
            } else {
                WS_VARIABLES.MO_NO_CNT += 1;
            }
        }
        if (PSCI8000.ORD_AC.trim().length() == 0) {
            if (PSCI8000.ORD_AC.equalsIgnoreCase(PSRRGSA.ORD_AC)) {
                WS_VARIABLES.MO_YE_CNT += 1;
            } else {
                WS_VARIABLES.MO_NO_CNT += 1;
            }
        }
        if (PSCI8000.BF_AC.trim().length() == 0) {
            if (PSCI8000.BF_AC.equalsIgnoreCase(PSRRGSA.BF_AC)) {
                WS_VARIABLES.MO_YE_CNT += 1;
            } else {
                WS_VARIABLES.MO_NO_CNT += 1;
            }
        }
        if (PSCI8000.CERT_NO.trim().length() == 0) {
            if (PSCI8000.CERT_NO.equalsIgnoreCase(PSRRGSA.CERT_NO)) {
                WS_VARIABLES.MO_YE_CNT += 1;
            } else {
                WS_VARIABLES.MO_NO_CNT += 1;
            }
        }
    }
    public void T999_A() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, WS_OUT_PS800);
        WS_OUT_PS800.OUT_RGN_CD = PSRRGSA.KEY.RGN_CD;
        WS_OUT_PS800.OUT_RGN_SEQ = PSRRGSA.KEY.RGN_SEQ;
        WS_OUT_PS800.OUT_IO_FL = PSRRGSA.IO_FLG;
        WS_OUT_PS800.OUT_R_TMS = PSRRGSA.R_TMS;
        WS_OUT_PS800.OUT_CCY = PSRRGSA.KEY.CCY;
        WS_OUT_PS800.OUT_TX_BR = PSRRGSA.TX_BR;
        WS_OUT_PS800.OUT_PAY_TYP = PSRRGSA.PAY_TYP;
        WS_OUT_PS800.OUT_SND_BR = "" + PSRRGSA.SND_BR;
        JIBS_tmp_int = WS_OUT_PS800.OUT_SND_BR.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) WS_OUT_PS800.OUT_SND_BR = "0" + WS_OUT_PS800.OUT_SND_BR;
        WS_OUT_PS800.OUT_REC_BR = "" + PSRRGSA.REC_BR;
        JIBS_tmp_int = WS_OUT_PS800.OUT_REC_BR.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) WS_OUT_PS800.OUT_REC_BR = "0" + WS_OUT_PS800.OUT_REC_BR;
        WS_OUT_PS800.OUT_AMT = PSRRGSA.AMT;
        WS_OUT_PS800.OUT_ORD_AC = PSRRGSA.ORD_AC;
        WS_OUT_PS800.OUT_BF_AC = PSRRGSA.BF_AC;
        WS_OUT_PS800.OUT_ORD_NM = PSRRGSA.ORD_NM;
        WS_OUT_PS800.OUT_BF_NM = PSRRGSA.BF_NM;
        WS_OUT_PS800.OUT_PAY_STS = PSRRGSA.PAY_STS.charAt(0);
        WS_OUT_PS800.OUT_VOUCH_CD = PSRRGSA.VOUCH_CD;
        WS_OUT_PS800.OUT_CERT_NO = PSRRGSA.CERT_NO;
        WS_OUT_PS800.OUT_SP_FD = PSRRGSA.SP_FD;
        WS_OUT_PS800.OUT_TX_DT = PSRRGSA.TX_DT;
        WS_OUT_PS800.OUT_TX_JRN = "" + PSRRGSA.TX_JRN;
        JIBS_tmp_int = WS_OUT_PS800.OUT_TX_JRN.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) WS_OUT_PS800.OUT_TX_JRN = "0" + WS_OUT_PS800.OUT_TX_JRN;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, WS_OUT_PS800);
        SCCMPAG.DATA_LEN = 623;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_PSZBRQRY() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, P_PSZBRQRY, PSCBRQRY);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, PSCBRQRY.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase("0")) {
            WS_VARIABLES.ERR_MSG = IBS.CLS2CPY(SCCGWA, PSCBRQRY.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_RGSA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "STARTBR");
        WS_DB2_VARIABLES.RGN_CD = PSCI8000.RGN_CD;
        WS_DB2_VARIABLES.RGN_SEQ = PSCI8000.RGN_SEQ;
        PSTRGSA_BR.rp = new DBParm();
        PSTRGSA_BR.rp.TableName = "PSTRGSA";
        PSTRGSA_BR.rp.where = "RGN_CD = :WS_DB2_VARIABLES.RGN_CD "
            + "AND RGN_SEQ = :WS_DB2_VARIABLES.RGN_SEQ";
        IBS.STARTBR(SCCGWA, PSRRGSA, this, PSTRGSA_BR);
    }
    public void T000_READNEXT_RGSA() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, PSRRGSA, this, PSTRGSA_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_VARIABLES.RGSA_FLG = 'Y';
            WS_VARIABLES.RGSA_CNT += 1;
        }
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_VARIABLES.RGSA_FLG = 'N';
        }
    }
    public void T000_ENDBR_RGSA() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, PSTRGSA_BR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_VARIABLES.ERR_MSG);
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
        CEP.TRC(SCCGWA, "PSOT8000 END");
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
