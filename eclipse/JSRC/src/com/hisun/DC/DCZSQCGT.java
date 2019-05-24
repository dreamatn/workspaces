package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.DD.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSQCGT {
    int JIBS_tmp_int;
    DBParm DCTCDDAT_RD;
    DBParm DCTDCICT_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_DCZUPSWM = "DC-U-PSW-MAINTAIN";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_OUTPUT_FMT = "DC337";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    String WS_PROD_CD = " ";
    String WS_HOLDER_CINO = " ";
    String WS_REL_DR_CARD = " ";
    String WS_ACAC_NO = " ";
    String WS_DB_AC_NO = " ";
    char WS_TBL_FLAG = ' ';
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    SCCWRMSG SCCWRMSG = new SCCWRMSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    DCRCDDAT DCRCDDAT = new DCRCDDAT();
    DCRDCICT DCRDCICT = new DCRDCICT();
    DCCUCINF DCCUCINF = new DCCUCINF();
    CICQACRL CICQACRL = new CICQACRL();
    CICQACAC CICQACAC = new CICQACAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    SCCCLDT SCCCLDT = new SCCCLDT();
    DCCF307 DCCF307 = new DCCF307();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    DCCSQCGT DCCSQCGT;
    public void MP(SCCGWA SCCGWA, DCCSQCGT DCCSQCGT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSQCGT = DCCSQCGT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSQCGT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        DCCSQCGT.IO_AREA.CHIP_BAL_AMT = 100;
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B040_AMT_CHANGE_PROCESS();
        if (pgmRtn) return;
        B050_ADD_DCTDCICT_INFO();
        if (pgmRtn) return;
        B070_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSQCGT.IO_AREA.CARD_NO);
        CEP.TRC(SCCGWA, DCCSQCGT.IO_AREA.CHIP_BAL_AMT);
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = DCCSQCGT.IO_AREA.CARD_NO;
        WS_REL_DR_CARD = DCCSQCGT.IO_AREA.CARD_NO;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'Y') {
            if (DCRCDDAT.EXP_DT < SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_CRT_EXPIRE;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
            IBS.init(SCCGWA, DCCUCINF);
            DCCUCINF.CARD_NO = DCCSQCGT.IO_AREA.CARD_NO;
            S000_CALL_DCZUCINF();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, DCCUCINF.CARD_PROD_FLG);
            if (DCCUCINF.CARD_PROD_FLG == 'S') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_ALLOW_CITZEN_CRD;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            } else if (DCCUCINF.CARD_PROD_FLG == 'Y') {
                CICQACRL.DATA.AC_NO = DCCSQCGT.IO_AREA.CARD_NO;
                CICQACRL.DATA.AC_REL = "03";
                CICQACRL.FUNC = 'I';
                S000_CALL_CIZQACRL();
                if (pgmRtn) return;
                WS_REL_DR_CARD = CICQACRL.O_DATA.O_REL_AC_NO;
                CEP.TRC(SCCGWA, CICQACRL.O_DATA.O_REL_AC_NO);
            }
        }
        CEP.TRC(SCCGWA, WS_REL_DR_CARD);
        IBS.init(SCCGWA, DCRCDDAT);
        DCRCDDAT.KEY.CARD_NO = WS_REL_DR_CARD;
        T000_READ_DCTCDDAT();
        if (pgmRtn) return;
        B012_CHECK_CARD_STSW();
        if (pgmRtn) return;
        if (DCCSQCGT.IO_AREA.CHIP_BAL_AMT <= 0) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CHIP_BAL_LE_ZERO;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void B012_CHECK_CARD_STSW() throws IOException,SQLException,Exception {
        if (DCRCDDAT.CARD_STS != 'N') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_NORMAL_STS;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(0, 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_ALREADY_LOSE;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
        if (DCRCDDAT.CARD_STSW == null) DCRCDDAT.CARD_STSW = "";
        JIBS_tmp_int = DCRCDDAT.CARD_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) DCRCDDAT.CARD_STSW += " ";
        if (DCRCDDAT.CARD_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_CARD_LOSS_STS;
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void B040_AMT_CHANGE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCUDRAC);
        DDCUDRAC.AC = DCCSQCGT.IO_AREA.CARD_NO;
        DDCUDRAC.CCY = "156";
        DDCUDRAC.TX_AMT = DCCSQCGT.IO_AREA.CHIP_BAL_AMT;
        DDCUDRAC.AID = "003";
        DDCUDRAC.TX_TYPE = 'T';
        DDCUDRAC.OTHER_AC = DCCSQCGT.IO_AREA.CARD_NO;
        DDCUDRAC.OTHER_CCY = "156";
        DDCUDRAC.OTHER_AMT = DCCSQCGT.IO_AREA.CHIP_BAL_AMT;
        DDCUDRAC.CHK_PSW_FLG = 'N';
        DDCUDRAC.SMS_FLG = 'N';
        DDCUDRAC.TX_MMO = SCCGWA.COMM_AREA.TR_MMO;
        S000_CALL_DDZUDRAC();
        if (pgmRtn) return;
        IBS.init(SCCGWA, DDCUCRAC);
        DDCUCRAC.AC = DCCSQCGT.IO_AREA.CARD_NO;
        DDCUCRAC.CCY = "156";
        DDCUCRAC.TX_AMT = DCCSQCGT.IO_AREA.CHIP_BAL_AMT;
        DDCUCRAC.OTHER_AC = DCCSQCGT.IO_AREA.CARD_NO;
        DDCUCRAC.OTHER_AMT = DCCSQCGT.IO_AREA.CHIP_BAL_AMT;
        DDCUCRAC.OTHER_CCY = "156";
        DDCUCRAC.TX_TYPE = 'T';
        DDCUCRAC.TX_MMO = "A001";
        CEP.TRC(SCCGWA, DDCUCRAC.TX_MMO);
        CEP.TRC(SCCGWA, DDCUCRAC.OTHER_AC);
        CEP.TRC(SCCGWA, DDCUCRAC.OTHER_AMT);
        S000_CALL_DDZUCRAC();
        if (pgmRtn) return;
    }
    public void B050_ADD_DCTDCICT_INFO() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSQCGT.IO_AREA.TXN_DATE);
        CEP.TRC(SCCGWA, DCCSQCGT.IO_AREA.TXN_JRNO);
        IBS.init(SCCGWA, DCRDCICT);
        DCRDCICT.KEY.TXN_DT = DCCSQCGT.IO_AREA.TXN_DATE;
        DCRDCICT.KEY.TXN_JANNO = DCCSQCGT.IO_AREA.TXN_JRNO;
        T000_READUPD_DCTDCICT();
        if (pgmRtn) return;
        DCRDCICT.TXN_STS = '0';
        DCRDCICT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRDCICT.UPDTBL_TLR = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        T000_REWRITE_DCTDCICT();
        if (pgmRtn) return;
    }
    public void B070_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCF307);
        DCCF307.TRD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCCF307.TRD_SEQNO = SCCGWA.COMM_AREA.JRN_NO;
        DCCF307.QC_OUT_AMT = DCCSQCGT.IO_AREA.CHIP_BAL_AMT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DCCF307;
        SCCFMT.DATA_LEN = 36;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_CALL_DCZUCINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DC-U-CARD-INF", DCCUCINF);
        if (DCCUCINF.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, DCCUCINF.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACRL() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACRL", CICQACRL);
        if (CICQACRL.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, CICQACRL.RC.RC_CODE);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACRL.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
        if (CICQACAC.RC.RC_CODE != 0) {
            CEP.TRC(SCCGWA, CICQACAC.RC.RC_CODE);
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, CICQACAC.RC);
            CEP.ERR(SCCGWA, WS_ERR_MSG);
        }
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void T000_READ_DCTCDDAT() throws IOException,SQLException,Exception {
        DCTCDDAT_RD = new DBParm();
        DCTCDDAT_RD.TableName = "DCTCDDAT";
        IBS.READ(SCCGWA, DCRCDDAT, DCTCDDAT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTCDDAT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_READUPD_DCTDCICT() throws IOException,SQLException,Exception {
        DCTDCICT_RD = new DBParm();
        DCTDCICT_RD.TableName = "DCTDCICT";
        DCTDCICT_RD.upd = true;
        IBS.READ(SCCGWA, DCRDCICT, DCTDCICT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_DCICT_NOT_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = "DCTDCICT";
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_REWRITE_DCTDCICT() throws IOException,SQLException,Exception {
        DCTDCICT_RD = new DBParm();
        DCTDCICT_RD.TableName = "DCTDCICT";
        IBS.REWRITE(SCCGWA, DCRDCICT, DCTDCICT_RD);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
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
        pgmRtn = true;
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
