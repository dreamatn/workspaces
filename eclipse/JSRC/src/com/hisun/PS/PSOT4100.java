package com.hisun.PS;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;
import com.hisun.BP.*;

import java.io.IOException;
import java.sql.SQLException;

public class PSOT4100 {
    int JIBS_tmp_int;
    DBParm PSTEINF_RD;
    DBParm PSTOBLL_RD;
    DBParm PSTBSP01_RD;
    String PGM_SCSSCLDT = "SCSSCLDT";
    String WS_ERR_MSG = " ";
    int WS_BSP01_LEN = 0;
    int WS_I = 0;
    int WS_A = 0;
    PSOT4100_WS_BATH_PARM WS_BATH_PARM = new PSOT4100_WS_BATH_PARM();
    String WS_AP_BATNO = " ";
    String WS_EXG_BK_NO = " ";
    String WS_EXG_AREA_NO = " ";
    String WS_OUR_EXG_NO = " ";
    String WS_EXG_CCY = " ";
    int WS_EXG_REPT_DT = 0;
    short WS_EXG_REPT_TMS = 0;
    char WS_EXG_REC_STS = ' ';
    char WS_EXG_REPT_FLG = ' ';
    int WS_TR_BR = 0;
    int WS_CNT = 0;
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
    PSCB4220 PSCB4220 = new PSCB4220();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCPQORG BPCPQORG = new BPCPQORG();
    PSRBSP01 PSRBSP01 = new PSRBSP01();
    PSCP4100 PSCP4100 = new PSCP4100();
    SCCRWBSP SCCRWBSP = new SCCRWBSP();
    SCCCLDT SCCCLDT = new SCCCLDT();
    PSROBLL PSROBLL = new PSROBLL();
    PSREINF PSREINF = new PSREINF();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    PSB4100_AWA_4100 PSB4100_AWA_4100;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        B100_READ_PSTOBLL();
        T000_CONUT_PSTOBLL();
        B020_GEN_BSP_RECORD();
        CEP.TRC(SCCGWA, "PSOT4100 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "PSB4100_AWA_4100>");
        PSB4100_AWA_4100 = (PSB4100_AWA_4100) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.BR_DP.TR_BRANCH);
        S000_CALL_BPZPQORG();
        WS_EXG_AREA_NO = "" + BPCPQORG.BRANCH_BR;
        JIBS_tmp_int = WS_EXG_AREA_NO.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) WS_EXG_AREA_NO = "0" + WS_EXG_AREA_NO;
        PSB4100_AWA_4100.AREA_NO = WS_EXG_AREA_NO;
        CEP.TRC(SCCGWA, BPCPQORG.BRANCH_BR);
        CEP.TRC(SCCGWA, PSB4100_AWA_4100.AREA_NO);
        if (PSB4100_AWA_4100.AREA_NO.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_AREA_NO_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSB4100_AWA_4100.EXG_CCY.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_CCY_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSB4100_AWA_4100.EXG_NO.trim().length() == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_EX_NO_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSB4100_AWA_4100.REPT_DT == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_STDT_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        if (PSB4100_AWA_4100.REPT_TMS == 0) {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_STTS_MST_INPUT;
            S000_ERR_MSG_PROC();
        }
        B010_CLEAR_PSTBSP01();
        CEP.TRC(SCCGWA, PSB4100_AWA_4100.AREA_NO);
        CEP.TRC(SCCGWA, PSB4100_AWA_4100.EXG_CCY);
        CEP.TRC(SCCGWA, PSB4100_AWA_4100.EXG_NO);
        CEP.TRC(SCCGWA, PSB4100_AWA_4100.REPT_DT);
        CEP.TRC(SCCGWA, PSB4100_AWA_4100.REPT_TMS);
        PSCP4100.EXG_AREA_NO = PSB4100_AWA_4100.AREA_NO;
        PSCP4100.EXG_CCY = PSB4100_AWA_4100.EXG_CCY;
        PSCP4100.OUR_EXG_NO = PSB4100_AWA_4100.EXG_NO;
        PSCP4100.EXG_REPT_DT = PSB4100_AWA_4100.REPT_DT;
        PSCP4100.EXG_REPT_TMS = PSB4100_AWA_4100.REPT_TMS;
        PSCP4100.TR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CEP.TRC(SCCGWA, PSCP4100.OUR_EXG_NO);
        CEP.TRC(SCCGWA, PSCP4100.TR_BR);
        CEP.TRC(SCCGWA, PSCP4100);
        WS_BSP01_LEN = 38;
    }
    public void B020_GEN_BSP_RECORD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSRBSP01);
        WS_I += 1;
        CEP.TRC(SCCGWA, "NCB0925001");
        CEP.TRC(SCCGWA, WS_I);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        PSRBSP01.KEY.AP_TYPE = "PS0060";
        PSRBSP01.KEY.AP_BATNO = SCCGWA.COMM_AREA.JRN_NO;
        PSRBSP01.KEY.BAT_NO = WS_I;
        PSRBSP01.BK = SCCGWA.COMM_AREA.TR_BANK;
        PSRBSP01.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        PSRBSP01.REQ_SYS = SCCGWA.COMM_AREA.REQ_SYSTEM;
        PSRBSP01.SYS_DT = SCCGWA.COMM_AREA.TR_DATE;
        PSRBSP01.AC_DATE = WS_BATH_PARM.WS_AC_DATE;
        PSRBSP01.RUN_TYPE = 'T';
        PSRBSP01.RUN_CMP_NAME = "0814100";
        PSRBSP01.ERR_ROLL_FLG = 'Y';
        PSRBSP01.TLT = SCCGWA.COMM_AREA.TL_ID;
        PSRBSP01.BLOB_TR_DATA = IBS.CLS2CPY(SCCGWA, PSCP4100);
        S000_LINK_PSTBSP01();
        S000_CALL_A_PSZSRCVE();
    }
    public void S000_LINK_PSTBSP01() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCRWBSP);
        CEP.TRC(SCCGWA, "111");
        SCCRWBSP.INFO.FUNC = 'C';
        SCCRWBSP.INFO.PTR = PSRBSP01;
        CEP.TRC(SCCGWA, "222");
        SCCRWBSP.INFO.LEN = 268;
        CEP.TRC(SCCGWA, "333");
        IBS.CALLCPN(SCCGWA, "PS-ADD-TQP", SCCRWBSP);
        CEP.TRC(SCCGWA, "444");
        if (SCCRWBSP.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, SCCRWBSP.RC);
        }
    }
    public void B010_CLEAR_PSTBSP01() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCLDT);
        SCCCLDT.DATE1 = SCCGWA.COMM_AREA.AC_DATE;
        SCCCLDT.DAYS = 1;
        CEP.TRC(SCCGWA, SCCCLDT);
        SCSSCLDT SCSSCLDT1 = new SCSSCLDT();
        SCSSCLDT1.MP(SCCGWA, SCCCLDT);
        CEP.TRC(SCCGWA, SCCCLDT.RC);
        CEP.TRC(SCCGWA, SCCCLDT.DATE1);
        CEP.TRC(SCCGWA, SCCCLDT.DATE2);
        CEP.TRC(SCCGWA, "B010-CLEAR-PSTBSP01 WS-AP-BATNO =");
        CEP.TRC(SCCGWA, WS_AP_BATNO);
        IBS.init(SCCGWA, PSRBSP01);
        PSRBSP01.AC_DATE = SCCCLDT.DATE2;
        T000_DELETE_PSTBSP01();
    }
    public void B100_READ_PSTOBLL() throws IOException,SQLException,Exception {
        S000_READ_PSTEINF();
    }
    public void S000_READ_PSTEINF() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSREINF);
        PSREINF.KEY.EXG_BK_NO = SCCGWA.COMM_AREA.TR_BANK;
        PSREINF.KEY.EXG_AREA_NO = WS_EXG_AREA_NO;
        CEP.TRC(SCCGWA, PSREINF.KEY.EXG_AREA_NO);
        PSREINF.KEY.EXG_CCY = PSB4100_AWA_4100.EXG_CCY;
        PSREINF.KEY.EXG_TX_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        PSTEINF_RD = new DBParm();
        PSTEINF_RD.TableName = "PSTEINF";
        IBS.READ(SCCGWA, PSREINF, PSTEINF_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_PSTOBLL_NOFUNT_ERR;
            S000_ERR_MSG_PROC();
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "PSTOBLL";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "REWRITE";
            B_DB_EXCP();
        }
        if (PSREINF.EXG_SYS_STS.equalsIgnoreCase("03")) {
        } else {
            CEP.TRC(SCCGWA, "ZHUANG TAI ");
            WS_ERR_MSG = PSCMSG_ERROR_MSG.PS_NOT_IN_RECEIPT;
            S000_ERR_MSG_PROC();
        }
    }
    public void T000_CONUT_PSTOBLL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, PSROBLL);
        WS_EXG_BK_NO = SCCGWA.COMM_AREA.TR_BANK;
        WS_OUR_EXG_NO = PSB4100_AWA_4100.EXG_NO;
        WS_EXG_REPT_DT = PSB4100_AWA_4100.REPT_DT;
        WS_EXG_REPT_TMS = PSB4100_AWA_4100.REPT_TMS;
        WS_EXG_CCY = PSB4100_AWA_4100.EXG_CCY;
        WS_EXG_REC_STS = '2';
        WS_EXG_REPT_FLG = '0';
        PSTOBLL_RD = new DBParm();
        PSTOBLL_RD.TableName = "PSTOBLL";
        PSTOBLL_RD.set = "WS-CNT=COUNT(*)";
        PSTOBLL_RD.where = "EXG_BK_NO = :WS_EXG_BK_NO "
            + "AND EXG_AREA_NO = :WS_EXG_AREA_NO "
            + "AND OUR_EXG_NO = :WS_OUR_EXG_NO "
            + "AND EXG_CCY = :WS_EXG_CCY "
            + "AND SHL_EXG_DT = :WS_EXG_REPT_DT "
            + "AND SHL_EXG_TMS = :WS_EXG_REPT_TMS "
            + "AND EXG_REC_STS = :WS_EXG_REC_STS "
            + "AND EXG_REPT_FLG = :WS_EXG_REPT_FLG";
        IBS.GROUP(SCCGWA, PSROBLL, this, PSTOBLL_RD);
        CEP.TRC(SCCGWA, WS_CNT);
        CEP.TRC(SCCGWA, "ji shu");
        if (WS_CNT == 0) {
            CEP.ERR(SCCGWA, PSCMSG_ERROR_MSG.PS_NO_RECORD);
        }
    }
    public void T000_DELETE_PSTBSP01() throws IOException,SQLException,Exception {
        PSTBSP01_RD = new DBParm();
        PSTBSP01_RD.TableName = "PSTBSP01";
        PSTBSP01_RD.where = "AC_DATE < :PSRBSP01.AC_DATE";
        IBS.DELETE(SCCGWA, PSRBSP01, this, PSTBSP01_RD);
    }
    public void S000_CALL_A_PSZSRCVE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "PS-RCVE-PROC", PSCP4100);
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            S000_ERR_MSG_PROC();
        }
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
