package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.DD.*;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSQCCL {
    DBParm DCTDCICT_RD;
    brParm DCTDCICT_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int WS_TXN_DT = 0;
    String WS_CARD_NO = " ";
    String CPN_DCZUPSWM = "DC-U-PSW-MAINTAIN";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_OUTPUT_FMT = "DC335";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    String WS_PROD_CD = " ";
    String WS_HOLDER_CINO = " ";
    String WS_REL_DR_CARD = " ";
    String WS_ACAC_NO = " ";
    String WS_COUNT_CNT = " ";
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
    DCCUPSWM DCCUPSWM = new DCCUPSWM();
    CICQACRL CICQACRL = new CICQACRL();
    CICQACAC CICQACAC = new CICQACAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    BPCUABOX BPCUABOX = new BPCUABOX();
    SCCCLDT SCCCLDT = new SCCCLDT();
    DCCF305 DCCF305 = new DCCF305();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    DCCSQCCL DCCSQCCL;
    public void MP(SCCGWA SCCGWA, DCCSQCCL DCCSQCCL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSQCCL = DCCSQCCL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSQCCL return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B030_GET_CARD_ACAC();
        if (pgmRtn) return;
        B040_AMT_CHANGE_PROCESS();
        if (pgmRtn) return;
        B050_UPD_DCTDCICT_INFO();
        if (pgmRtn) return;
        B070_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSQCCL.IO_AREA.OLD_JRNNO);
        CEP.TRC(SCCGWA, DCCSQCCL.IO_AREA.CARD_NO);
        CEP.TRC(SCCGWA, DCCSQCCL.IO_AREA.CANCEL_AMT);
        CEP.TRC(SCCGWA, DCCSQCCL.IO_AREA.BALANCE_AMT);
        CEP.TRC(SCCGWA, DCCSQCCL.IO_AREA.QC_TYP);
        IBS.init(SCCGWA, DCRDCICT);
        DCRDCICT.KEY.TXN_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRDCICT.KEY.TXN_JANNO = DCCSQCCL.IO_AREA.OLD_JRNNO;
        T000_READ_DCTDCICT();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'Y') {
            if (DCRDCICT.KEY.TXN_DT != SCCGWA.COMM_AREA.AC_DATE) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_EQ_CURR_DT;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
            if (!DCRDCICT.TXN_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_GWA_TLR;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
            if (DCRDCICT.WRITE_CARD_STS != '2') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_SUCC_STS;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
            if (DCRDCICT.TXN_STS != '0') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_TXN_SUCCESS;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
            if (DCRDCICT.TXN_AMT != DCCSQCCL.IO_AREA.CANCEL_AMT) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_QC_EQ_TXN_AMT;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
            if (DCCSQCCL.IO_AREA.BALANCE_AMT <= DCCSQCCL.IO_AREA.CANCEL_AMT) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_BAL_LES_CANC_AMT;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
            if (DCRDCICT.TXN_STS == '3') {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_TXN_STS_CANCELED;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
            WS_COUNT_CNT = DCRDCICT.COUNT_CNT;
        }
        IBS.init(SCCGWA, DCRDCICT);
        WS_TXN_DT = SCCGWA.COMM_AREA.AC_DATE;
        WS_CARD_NO = DCCSQCCL.IO_AREA.CARD_NO;
        T000_CHECK_DCTDCICT();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'Y') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_QUANCUN_CANCEL;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, DCRDCICT);
        WS_TXN_DT = SCCGWA.COMM_AREA.AC_DATE;
        WS_CARD_NO = DCCSQCCL.IO_AREA.CARD_NO;
        T000_STARTBR_DCTDCICT();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_COUNT_CNT);
        CEP.TRC(SCCGWA, DCRDCICT.COUNT_CNT);
        while (WS_TBL_FLAG != 'N') {
            if (DCRDCICT.COUNT_CNT.compareTo(WS_COUNT_CNT) > 0) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_ALR_QUANCUN_TRD;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B030_GET_CARD_ACAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'I';
        CICQACAC.DATA.AGR_NO = DCCSQCCL.IO_AREA.CARD_NO;
        CICQACAC.DATA.AID = "003";
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        WS_ACAC_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
    }
    public void B040_AMT_CHANGE_PROCESS() throws IOException,SQLException,Exception {
        if (DCCSQCCL.IO_AREA.QC_TYP == '2') {
            IBS.init(SCCGWA, DDCUCRAC);
            DDCUCRAC.AC = DCCSQCCL.IO_AREA.CARD_NO;
            DDCUCRAC.CCY = "156";
            DDCUCRAC.TX_AMT = DCCSQCCL.IO_AREA.CANCEL_AMT;
            DDCUCRAC.AID = "003";
            S000_CALL_DDZUCRAC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, BPCUABOX);
            BPCUABOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            BPCUABOX.TLR = SCCGWA.COMM_AREA.TL_ID;
            BPCUABOX.CCY = "156";
            BPCUABOX.AMT = DCCSQCCL.IO_AREA.CANCEL_AMT;
            CEP.TRC(SCCGWA, BPCUABOX.CCY);
            CEP.TRC(SCCGWA, BPCUABOX.AMT);
            BPCUABOX.OPP_AC = WS_ACAC_NO;
            S000_CALL_BPZUABOX();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, DCCSQCCL.IO_AREA.CARD_NO);
            IBS.init(SCCGWA, DDCUDRAC);
            DDCUDRAC.AC = WS_ACAC_NO;
            DDCUDRAC.CCY = "156";
            DDCUDRAC.TX_AMT = DCCSQCCL.IO_AREA.CANCEL_AMT;
            DDCUDRAC.TX_TYPE = 'T';
            DDCUDRAC.OTHER_CCY = "1";
            DDCUDRAC.OTHER_AMT = DCCSQCCL.IO_AREA.CANCEL_AMT;
            S000_CALL_DDZUDRAC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DDCUCRAC);
            DDCUCRAC.CCY = "156";
            DDCUCRAC.TX_AMT = DCCSQCCL.IO_AREA.CANCEL_AMT;
            S000_CALL_DDZUCRAC();
            if (pgmRtn) return;
        }
    }
    public void B050_UPD_DCTDCICT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRDCICT);
        DCRDCICT.KEY.TXN_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRDCICT.KEY.TXN_JANNO = DCCSQCCL.IO_AREA.OLD_JRNNO;
        T000_READUPD_DCTDCICT();
        if (pgmRtn) return;
        DCRDCICT.KEY.TXN_JANNO = SCCGWA.COMM_AREA.JRN_NO;
        DCRDCICT.TXN_STS = '3';
        DCRDCICT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRDCICT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DCTDCICT();
        if (pgmRtn) return;
    }
    public void B070_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCF305);
        DCCF305.TRD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCCF305.TRD_SEQNO = SCCGWA.COMM_AREA.JRN_NO;
        DCCF305.OLD_TRD_SEQNO = DCCSQCCL.IO_AREA.OLD_JRNNO;
        DCCF305.CALCEL_AMT = DCCSQCCL.IO_AREA.CANCEL_AMT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DCCF305;
        SCCFMT.DATA_LEN = 48;
        IBS.FMT(SCCGWA, SCCFMT);
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
    public void S000_CALL_BPZUABOX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-U-ADD-CBOX", BPCUABOX);
    }
    public void S000_CALL_DDZUCRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DEP-PROC", DDCUCRAC);
    }
    public void S000_CALL_DDZUDRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-UNIT-DRAW-PROC", DDCUDRAC);
    }
    public void T000_READ_DCTDCICT() throws IOException,SQLException,Exception {
        DCTDCICT_RD = new DBParm();
        DCTDCICT_RD.TableName = "DCTDCICT";
        IBS.READ(SCCGWA, DCRDCICT, DCTDCICT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
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
    public void T000_READUPD_DCTDCICT() throws IOException,SQLException,Exception {
        DCTDCICT_RD = new DBParm();
        DCTDCICT_RD.TableName = "DCTDCICT";
        DCTDCICT_RD.upd = true;
        IBS.READ(SCCGWA, DCRDCICT, DCTDCICT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_CARD_NOT_EXIST;
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
    public void T000_UPDATE_DCTDCICT() throws IOException,SQLException,Exception {
        DCTDCICT_RD = new DBParm();
        DCTDCICT_RD.TableName = "DCTDCICT";
        IBS.REWRITE(SCCGWA, DCRDCICT, DCTDCICT_RD);
    }
    public void T000_CHECK_DCTDCICT() throws IOException,SQLException,Exception {
        DCTDCICT_RD = new DBParm();
        DCTDCICT_RD.TableName = "DCTDCICT";
        DCTDCICT_RD.where = "TXN_DT = :WS_TXN_DT "
            + "AND CARD_NO = :WS_CARD_NO "
            + "AND TXN_TYP = '7'";
        IBS.READ(SCCGWA, DCRDCICT, this, DCTDCICT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
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
    public void T000_STARTBR_DCTDCICT() throws IOException,SQLException,Exception {
        DCTDCICT_BR.rp = new DBParm();
        DCTDCICT_BR.rp.TableName = "DCTDCICT";
        DCTDCICT_BR.rp.where = "TXN_DT = :WS_TXN_DT "
            + "AND CARD_NO = :WS_CARD_NO "
            + "AND ( TXN_TYP = '0' "
            + "OR TXN_TYP = '1' "
            + "OR TXN_TYP = '2' )";
        IBS.STARTBR(SCCGWA, DCRDCICT, this, DCTDCICT_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
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
