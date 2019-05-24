package com.hisun.PS;

import com.hisun.CI.*;
import com.hisun.AI.*;
import com.hisun.DD.*;
import com.hisun.BP.*;
import com.hisun.SC.*;

import java.io.IOException;
import java.sql.SQLException;

public class PSZBSP01 {
    DBParm PSTOBLL_RD;
    DBParm PSTEINF_RD;
    String K_EXG_BK_NO = "001";
    String CPN_U_BPZPOEWA = "BP-P-WRT-ONL-EWA";
    String K_CNTR_TYPE1 = "LOCL";
    String K_EVENT_CODE2 = "OWDRP";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    short WS_NUM = 0;
    String WS_BRAC = " ";
    char WS_TABLE_FLG = ' ';
    char WS_EXG_REC_STS = ' ';
    CICQACRI CICQACRI = new CICQACRI();
    AICPQIA AICPQIA = new AICPQIA();
    AICUUPIA AICUUPIA = new AICUUPIA();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCFMT SCCFMT = new SCCFMT();
    PSROBLL PSROBLL = new PSROBLL();
    PSRPBIN PSRPBIN = new PSRPBIN();
    PSREINF PSREINF = new PSREINF();
    String WK_RGN_CD = " ";
    String WK_RGN_SEQ = " ";
    String WK_CCY = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PSCBSP01 PSCBSP01;
    public void MP(SCCGWA SCCGWA, PSCBSP01 PSCBSP01) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.PSCBSP01 = PSCBSP01;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "PSZBSP01 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B100_CHECK_INPUT_DATA();
        B200_CHECK_TRAN_DATA();
        B210_ACC_TREA();
        B300_WRITE_PSREINF();
    }
    public void B100_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (PSCBSP01.EXG_AREA_NO.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_AREA_NO_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSCBSP01.OUR_EXG_NO.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_EX_NO_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSCBSP01.EXG_CCY.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_CCY_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSCBSP01.EXG_REPT_DT == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_STDT_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSCBSP01.EXG_REPT_TMS == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_STTS_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSCBSP01.EXG_RT_DT == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_TCDT_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSCBSP01.EXG_RT_TMS == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_TCTMS_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSCBSP01.TX_JRNNO == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_TX_JRN_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSCBSP01.OUR_ACNO.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_BF_AC_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSCBSP01.OUR_ACNM.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_BF_NM_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSCBSP01.CASH_ID == ' ') {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_CASH_ID_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B200_CHECK_TRAN_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSREINF);
        PSREINF.KEY.EXG_BK_NO = PSCBSP01.BK_NO;
        PSREINF.KEY.EXG_AREA_NO = PSCBSP01.EXG_AREA_NO;
        PSREINF.KEY.EXG_CCY = PSCBSP01.EXG_CCY;
        PSREINF.KEY.EXG_TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, PSREINF.KEY.EXG_BK_NO);
        CEP.TRC(SCCGWA, PSREINF.KEY.EXG_AREA_NO);
        CEP.TRC(SCCGWA, PSREINF.KEY.EXG_CCY);
        CEP.TRC(SCCGWA, PSREINF.KEY.EXG_TX_BR);
        CEP.TRC(SCCGWA, PSREINF.EXG_SYS_STS);
        T000_READ_PSTEINF();
        CEP.TRC(SCCGWA, PSREINF.EXG_NO);
        CEP.TRC(SCCGWA, PSREINF.EXG_YEND_TMS);
        CEP.TRC(SCCGWA, PSREINF.EXG_JOIN_TMS);
        CEP.TRC(SCCGWA, PSREINF.EXG_YEND_TMS);
        CEP.TRC(SCCGWA, PSREINF.EXG_SYS_STS);
        IBS.init(SCCGWA, PSROBLL);
        PSROBLL.KEY.EXG_BK_NO = PSCBSP01.BK_NO;
        PSROBLL.KEY.EXG_AREA_NO = PSCBSP01.EXG_AREA_NO;
        PSROBLL.KEY.EXG_DT = PSCBSP01.EXG_RT_DT;
        PSROBLL.KEY.EXG_TMS = PSCBSP01.EXG_RT_TMS;
        PSROBLL.KEY.EXG_JRN_NO = PSCBSP01.TX_JRNNO;
        T000_READ_PSTOBLL();
        if (PSCBSP01.EXG_REPT_DT < PSROBLL.SHL_EXG_DT) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_REC_DT_NOT_RIGHT;
            S000_ERR_MSG_PROC();
        }
        if (PSROBLL.EXG_REPT_FLG == '1') {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_OVST_MST_RECEIPT;
            S000_ERR_MSG_PROC();
        }
    }
    public void B210_ACC_TREA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPOEWA);
        BPCPOEWA.DATA.CNTR_TYPE = K_CNTR_TYPE1;
        BPCPOEWA.DATA.EVENT_CODE = K_EVENT_CODE2;
        BPCPOEWA.DATA.BR_OLD = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.BR_NEW = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCPOEWA.DATA.CCY_INFO[1-1].CCY = PSCBSP01.EXG_CCY;
        BPCPOEWA.DATA.AMT_INFO[1-1].AMT = PSCBSP01.EXG_AMT;
        BPCPOEWA.DATA.PROD_CODE = "7685960000";
        BPCPOEWA.DATA.AC_NO = PSCBSP01.EXG_CERT_NO;
        S000_CALL_BPZPOEWA();
        if (PSCBSP01.OUR_ACNO.trim().length() > 0) {
            IBS.init(SCCGWA, CICQACRI);
            CICQACRI.DATA.AGR_NO = PSCBSP01.OUR_ACNO;
            CICQACRI.FUNC = 'A';
            S000_CALL_CIZQACRI();
            CEP.TRC(SCCGWA, CICQACRI.O_DATA.O_FRM_APP);
        }
        if (!CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC") 
            && !CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD") 
            && !CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
            CEP.TRC(SCCGWA, "ERR AC-TYPE");
            CEP.ERR(SCCGWA, PSCMSG_ERROR_MSG.PS_AC_TYP_NOT_ALLOW);
        }
        if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DD") 
            || CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("DC")) {
            B220_DD_DR_DDZUCRAC();
        } else {
            if (CICQACRI.O_DATA.O_FRM_APP.equalsIgnoreCase("AI")) {
                IBS.init(SCCGWA, AICUUPIA);
                AICUUPIA.DATA.AC_NO = PSCBSP01.OUR_ACNO;
                AICUUPIA.DATA.CCY = PSCBSP01.EXG_CCY;
                AICUUPIA.DATA.AMT = PSCBSP01.EXG_AMT;
                AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
                AICUUPIA.DATA.PAY_MAN = PSCBSP01.OTH_ACNM;
                AICUUPIA.DATA.EVENT_CODE = "CR";
                CEP.TRC(SCCGWA, AICUUPIA.DATA.AC_NO);
                CEP.TRC(SCCGWA, AICUUPIA.DATA.AMT);
                CEP.TRC(SCCGWA, AICUUPIA.DATA.RVS_NO);
                S000_CALL_AIZUUPIA();
            }
        }
    }
    public void B220_DD_DR_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.AC = PSCBSP01.OUR_ACNO;
        CEP.TRC(SCCGWA, DDCUCRAC.AC);
        DDCUCRAC.CCY = PSCBSP01.EXG_CCY;
        DDCUCRAC.CCY_TYPE = '1';
        DDCUCRAC.TX_AMT = PSCBSP01.EXG_AMT;
        DDCUCRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DDCUCRAC.OTHER_AC = " ";
        DDCUCRAC.OTHER_CCY = " ";
        DDCUCRAC.TX_TYPE = 'T';
        DDCUCRAC.BANK_CR_FLG = 'Y';
        DDCUCRAC.TX_MMO = "A311";
        CEP.TRC(SCCGWA, DDCUCRAC.CCY_TYPE);
        S000_CALL_DDZUCRAC();
    }
    public void B300_WRITE_PSREINF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSROBLL);
        PSROBLL.KEY.EXG_BK_NO = PSCBSP01.BK_NO;
        PSROBLL.KEY.EXG_AREA_NO = PSCBSP01.EXG_AREA_NO;
        PSROBLL.KEY.EXG_DT = PSCBSP01.EXG_RT_DT;
        PSROBLL.KEY.EXG_TMS = PSCBSP01.EXG_RT_TMS;
        PSROBLL.KEY.EXG_JRN_NO = PSCBSP01.TX_JRNNO;
        CEP.TRC(SCCGWA, PSCBSP01.BK_NO);
        CEP.TRC(SCCGWA, PSCBSP01.EXG_AREA_NO);
        CEP.TRC(SCCGWA, PSCBSP01.EXG_REPT_DT);
        CEP.TRC(SCCGWA, PSCBSP01.EXG_REPT_TMS);
        CEP.TRC(SCCGWA, PSCBSP01.TX_JRNNO);
        PSTOBLL_RD = new DBParm();
        PSTOBLL_RD.TableName = "PSTOBLL";
        PSTOBLL_RD.upd = true;
        IBS.READ(SCCGWA, PSROBLL, PSTOBLL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        CEP.TRC(SCCGWA, PSROBLL.EXG_REPT_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_RGS_NO_REC;
            S000_ERR_MSG_PROC();
        } else {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_ERR_TABLE_EXIST;
            S000_ERR_MSG_PROC();
        }
        if (PSROBLL.EXG_REPT_FLG == '1') {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_OVST_MST_RECEIPT;
            S000_ERR_MSG_PROC();
        }
        WS_EXG_REC_STS = '1';
        PSROBLL.EXG_REC_STS = WS_EXG_REC_STS;
        PSROBLL.ACT_EXG_DT = PSCBSP01.EXG_REPT_DT;
        PSROBLL.ACT_EXG_TMS = PSCBSP01.EXG_REPT_TMS;
        PSROBLL.EXG_REPT_FLG = '1';
        PSTOBLL_RD = new DBParm();
        PSTOBLL_RD.TableName = "PSTOBLL";
        IBS.REWRITE(SCCGWA, PSROBLL, PSTOBLL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_DUKEY_REC_EXIST;
            CEP.TRC(SCCGWA, WS_TABLE_FLG);
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PSTOBLL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
        }
    }
    public void T000_READ_PSTEINF() throws IOException,SQLException,Exception {
        PSTEINF_RD = new DBParm();
        PSTEINF_RD.TableName = "PSTEINF";
        IBS.READ(SCCGWA, PSREINF, PSTEINF_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_RGS_NO_REC;
            S000_ERR_MSG_PROC();
        } else {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_ERR_TABLE_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_READ_PSTOBLL() throws IOException,SQLException,Exception {
        PSTOBLL_RD = new DBParm();
        PSTOBLL_RD.TableName = "PSTOBLL";
        IBS.READ(SCCGWA, PSROBLL, PSTOBLL_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_RGS_NO_REC;
            S000_ERR_MSG_PROC();
        } else {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_ERR_TABLE_EXIST;
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_CIZQACRI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACR-INF", CICQACRI);
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_AIZPQIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-IA", AICPQIA);
        CEP.TRC(SCCGWA, AICPQIA.AC);
        if (AICPQIA.RC.RC_CODE != 0 
            || AICPQIA.AC.trim().length() == 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, AICPQIA.RC);
            S000_ERR_MSG_PROC();
        }
    }
    public void S000_CALL_AIZUUPIA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-U-UPDATE-IA", AICUUPIA);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
