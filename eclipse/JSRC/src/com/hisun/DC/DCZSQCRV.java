package com.hisun.DC;

import com.hisun.SC.*;
import com.hisun.CI.*;
import com.hisun.DD.*;
import com.hisun.BP.*;
import com.hisun.AI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DCZSQCRV {
    DBParm DCTDCICT_RD;
    brParm DCTDCICT_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    int WS_TXN_DT = 0;
    String WS_CARD_NO = " ";
    String CPN_DCZUPSWM = "DC-U-PSW-MAINTAIN";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String K_OUTPUT_FMT = "DC339";
    String WS_ERR_MSG = " ";
    int WS_CNT = 0;
    String WS_PROD_CD = " ";
    String WS_HOLDER_CINO = " ";
    String WS_REL_DR_CARD = " ";
    String WS_ACAC_NO = " ";
    char WS_WRITE_CARD_STS = ' ';
    char WS_TXN_STS = ' ';
    String WS_COUNT_CNT = " ";
    String WS_CI_NM = " ";
    String WS_STL_AC_NM = " ";
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
    CICQACAC CICQACAC = new CICQACAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    BPCUABOX BPCUABOX = new BPCUABOX();
    SCCCLDT SCCCLDT = new SCCCLDT();
    AICPQMIB AICPQMIB = new AICPQMIB();
    CICCUST CICCUST = new CICCUST();
    DDCSTRAC DDCSTRAC = new DDCSTRAC();
    DCCF309 DCCF309 = new DCCF309();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
    DCCSQCRV DCCSQCRV;
    public void MP(SCCGWA SCCGWA, DCCSQCRV DCCSQCRV) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DCCSQCRV = DCCSQCRV;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DCZSQCRV return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPCPORUP = (BPCPORUP_DATA_INFO) SCCGWA.COMM_AREA.TR_BR_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_DCTDCICT_STS();
        if (pgmRtn) return;
        B030_GET_CARD_ACAC();
        if (pgmRtn) return;
        B040_AMT_CHANGE_PROCESS();
        if (pgmRtn) return;
        B050_UPD_DCTDCICT_INFO();
        if (pgmRtn) return;
        B060_ADD_DCTDCICT_INFO();
        if (pgmRtn) return;
        B070_OUTPUT_PROCESS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_DCTDCICT_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRDCICT);
        DCRDCICT.KEY.TXN_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        DCRDCICT.KEY.TXN_JANNO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        T000_READ_DCTDCICT();
        if (pgmRtn) return;
        if (WS_TBL_FLAG == 'Y') {
            if (DCRDCICT.KEY.TXN_DT != GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_EQ_CURR_DT;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
            if (!DCRDCICT.TXN_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_GWA_TLR;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
            if (DCRDCICT.TXN_AMT != DCCSQCRV.IO_AREA.REVERSAL_AMT) {
                WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_NOT_QC_EQ_TXN_AMT;
                CEP.ERR(SCCGWA, WS_ERR_MSG);
            }
            WS_WRITE_CARD_STS = DCRDCICT.WRITE_CARD_STS;
            WS_TXN_STS = DCRDCICT.TXN_STS;
            WS_COUNT_CNT = DCRDCICT.COUNT_CNT;
        }
    }
    public void B030_GET_CARD_ACAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        CICQACAC.FUNC = 'I';
        CICQACAC.DATA.AGR_NO = DCCSQCRV.IO_AREA.CARD_NO;
        CICQACAC.DATA.AID = "003";
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO);
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        WS_ACAC_NO = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
    }
    public void B040_AMT_CHANGE_PROCESS() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DCCSQCRV.IO_AREA.QC_TYP);
        CEP.TRC(SCCGWA, DCCSQCRV.IO_AREA.SLT_AC);
        if (DCCSQCRV.IO_AREA.QC_TYP == '2') {
            if (DCCSQCRV.IO_AREA.SLT_AC.trim().length() == 0) {
                IBS.init(SCCGWA, BPCUABOX);
                BPCUABOX.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPCUABOX.TLR = SCCGWA.COMM_AREA.TL_ID;
                BPCUABOX.CCY = "156";
                BPCUABOX.AMT = DCCSQCRV.IO_AREA.REVERSAL_AMT;
                CEP.TRC(SCCGWA, BPCUABOX.CCY);
                CEP.TRC(SCCGWA, BPCUABOX.AMT);
                BPCUABOX.OPP_AC = WS_ACAC_NO;
                S000_CALL_BPZUABOX();
                if (pgmRtn) return;
                IBS.init(SCCGWA, DDCUCRAC);
                DDCUCRAC.AC = DCCSQCRV.IO_AREA.CARD_NO;
                DDCUCRAC.CCY = "156";
                DDCUCRAC.TX_AMT = DCCSQCRV.IO_AREA.REVERSAL_AMT;
                DDCUCRAC.AID = "003";
                DDCUCRAC.TX_TYPE = 'C';
                if (WS_WRITE_CARD_STS == '3' 
                    && WS_TXN_STS == '0') {
                    DDCUCRAC.TX_MMO = "A036";
                }
                if (WS_WRITE_CARD_STS == '2' 
                    && WS_TXN_STS == '0') {
                    DDCUCRAC.TX_MMO = "A037";
                }
                S000_CALL_DDZUCRAC();
                if (pgmRtn) return;
            }
        } else {
            IBS.init(SCCGWA, DDCUDRAC);
            if (DCCSQCRV.IO_AREA.QC_TYP == '0') {
                DDCUDRAC.AC = DCCSQCRV.IO_AREA.CARD_NO;
            } else {
                DDCUDRAC.AC = DCCSQCRV.IO_AREA.UNASSIGN_CRD;
            }
            DDCUDRAC.CCY = "156";
            DDCUDRAC.TX_AMT = DCCSQCRV.IO_AREA.REVERSAL_AMT;
            DDCUDRAC.TX_TYPE = 'T';
            DDCUDRAC.OTHER_AC = DCCSQCRV.IO_AREA.CARD_NO;
            DDCUDRAC.OTHER_CCY = "156";
            DDCUDRAC.OTHER_AMT = DCCSQCRV.IO_AREA.REVERSAL_AMT;
            DDCUCRAC.TX_TYPE = 'T';
            DDCUDRAC.CHK_PSW_FLG = 'N';
            S000_CALL_DDZUDRAC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DDCUCRAC);
            DDCUCRAC.AC = DCCSQCRV.IO_AREA.CARD_NO;
            DDCUCRAC.CCY = "156";
            DDCUCRAC.TX_AMT = DCCSQCRV.IO_AREA.REVERSAL_AMT;
            DDCUCRAC.AID = "003";
            DDCUCRAC.TX_TYPE = 'T';
            if (DCCSQCRV.IO_AREA.QC_TYP == '0') {
                DDCUCRAC.OTHER_AC = DCCSQCRV.IO_AREA.CARD_NO;
            } else {
                DDCUCRAC.OTHER_AC = DCCSQCRV.IO_AREA.UNASSIGN_CRD;
            }
            DDCUCRAC.OTHER_AMT = DCCSQCRV.IO_AREA.REVERSAL_AMT;
            DDCUCRAC.OTHER_CCY = "156";
            if (WS_WRITE_CARD_STS == '3' 
                && WS_TXN_STS == '0') {
                DDCUCRAC.TX_MMO = "A036";
            }
            if (WS_WRITE_CARD_STS == '2' 
                && WS_TXN_STS == '0') {
                DDCUCRAC.TX_MMO = "A037";
            }
            S000_CALL_DDZUCRAC();
            if (pgmRtn) return;
        }
        if (DCCSQCRV.IO_AREA.QC_TYP == '2' 
            && DCCSQCRV.IO_AREA.SLT_AC.trim().length() > 0) {
            IBS.init(SCCGWA, AICPQMIB);
            AICPQMIB.INPUT_DATA.AC = DCCSQCRV.IO_AREA.SLT_AC;
            S000_CALL_AIZPQMIB();
            if (pgmRtn) return;
            WS_STL_AC_NM = AICPQMIB.OUTPUT_DATA.CHS_NM;
            IBS.init(SCCGWA, CICCUST);
            CICCUST.FUNC = 'A';
            CICCUST.DATA.AGR_NO = DCCSQCRV.IO_AREA.CARD_NO;
            S000_CALL_CIZCUST();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_NM);
            WS_CI_NM = CICCUST.O_DATA.O_CI_NM;
            IBS.init(SCCGWA, DDCSTRAC);
            DDCSTRAC.FR_AC = DCCSQCRV.IO_AREA.SLT_AC;
            DDCSTRAC.FR_BV_TYPE = '3';
            DDCSTRAC.FR_CCY = "156";
            DDCSTRAC.FR_AMT = DCCSQCRV.IO_AREA.REVERSAL_AMT;
            DDCSTRAC.FR_AC_CNAME = WS_STL_AC_NM;
            DDCSTRAC.TO_CARD = DCCSQCRV.IO_AREA.CARD_NO;
            DDCSTRAC.RLT_AC = DCCSQCRV.IO_AREA.CARD_NO;
            DDCSTRAC.TO_CCY = "156";
            DDCSTRAC.TO_AMT = DCCSQCRV.IO_AREA.REVERSAL_AMT;
            DDCSTRAC.TO_AC_CNAME = WS_CI_NM;
            DDCSTRAC.VAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDCSTRAC.TXN_REGION = '0';
            DDCSTRAC.TXN_CHNL = SCCGWA.COMM_AREA.CHNL;
            DDCSTRAC.TXN_TYPE = "02";
            DDCSTRAC.IN_OUT_FLG = '2';
            S000_CALL_DDZSTRAC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DDCUDRAC);
            DDCUDRAC.AC = DCCSQCRV.IO_AREA.CARD_NO;
            DDCUDRAC.CCY = "156";
            DDCUDRAC.TX_AMT = DCCSQCRV.IO_AREA.REVERSAL_AMT;
            DDCUDRAC.TX_TYPE = 'T';
            DDCUDRAC.OTHER_AC = DCCSQCRV.IO_AREA.CARD_NO;
            DDCUDRAC.OTHER_CCY = "156";
            DDCUDRAC.OTHER_AMT = DCCSQCRV.IO_AREA.REVERSAL_AMT;
            DDCUDRAC.CHK_PSW_FLG = 'N';
            DDCUDRAC.TX_MMO = SCCGWA.COMM_AREA.TR_MMO;
            DDCUCRAC.TX_TYPE = 'T';
            S000_CALL_DDZUDRAC();
            if (pgmRtn) return;
            IBS.init(SCCGWA, DDCUCRAC);
            DDCUCRAC.AC = DCCSQCRV.IO_AREA.CARD_NO;
            DDCUCRAC.CCY = "156";
            DDCUCRAC.TX_AMT = DCCSQCRV.IO_AREA.REVERSAL_AMT;
            DDCUCRAC.AID = "003";
            DDCUCRAC.TX_MMO = SCCGWA.COMM_AREA.TR_MMO;
            DDCUCRAC.TX_TYPE = 'T';
            if (WS_WRITE_CARD_STS == '3' 
                && WS_TXN_STS == '0') {
                DDCUCRAC.TX_MMO = "A036";
            }
            if (WS_WRITE_CARD_STS == '2' 
                && WS_TXN_STS == '0') {
                DDCUCRAC.TX_MMO = "A037";
            }
            S000_CALL_DDZUCRAC();
            if (pgmRtn) return;
        }
    }
    public void B050_UPD_DCTDCICT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRDCICT);
        DCRDCICT.KEY.TXN_DT = GWA_BP_AREA.CANCEL_AREA.CAN_AC_DATE;
        DCRDCICT.KEY.TXN_JANNO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CANCEL_JRN_NO);
        T000_READUPD_DCTDCICT();
        if (pgmRtn) return;
        if ((!SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10101") 
            && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("10102"))) {
            DCRDCICT.WRITE_CARD_STS = '3';
            WS_WRITE_CARD_STS = '3';
        }
        if (WS_WRITE_CARD_STS == '3' 
            && WS_TXN_STS == '0') {
            DCRDCICT.TXN_STS = '2';
        }
        if (WS_WRITE_CARD_STS == '2' 
            && WS_TXN_STS == '0') {
            DCRDCICT.TXN_STS = '3';
        }
        DCRDCICT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRDCICT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_UPDATE_DCTDCICT();
        if (pgmRtn) return;
    }
    public void B060_ADD_DCTDCICT_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCRDCICT);
        DCRDCICT.KEY.TXN_DT = SCCGWA.COMM_AREA.AC_DATE;
        DCRDCICT.KEY.TXN_JANNO = SCCGWA.COMM_AREA.JRN_NO;
        DCRDCICT.CARD_NO = DCCSQCRV.IO_AREA.CARD_NO;
        if (WS_WRITE_CARD_STS == '3' 
            && WS_TXN_STS == '0') {
            DCRDCICT.TXN_TYP = '6';
            DCRDCICT.WRITE_CARD_STS = '0';
        }
        if (WS_WRITE_CARD_STS == '2' 
            && WS_TXN_STS == '0') {
            DCRDCICT.TXN_TYP = '7';
            DCRDCICT.WRITE_CARD_STS = '1';
        }
        DCRDCICT.TXN_AMT = DCCSQCRV.IO_AREA.REVERSAL_AMT;
        DCRDCICT.TXN_CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        DCRDCICT.TXN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        DCRDCICT.TXN_TLR = SCCGWA.COMM_AREA.TL_ID;
        DCRDCICT.TXN_STS = '0';
        DCRDCICT.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCRDCICT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_WRITE_DCTDCICT();
        if (pgmRtn) return;
    }
    public void B070_OUTPUT_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCF309);
        DCCF309.TRD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        DCCF309.TRD_SEQNO = SCCGWA.COMM_AREA.JRN_NO;
        DCCF309.OLD_TRD_SEQNO = SCCGWA.COMM_AREA.CANCEL_JRN_NO;
        DCCF309.TXN_AMT = DCCSQCRV.IO_AREA.REVERSAL_AMT;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DCCF309;
        SCCFMT.DATA_LEN = 48;
        IBS.FMT(SCCGWA, SCCFMT);
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
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB, true);
        CEP.TRC(SCCGWA, AICPQMIB.RC.RC_CODE);
        if (AICPQMIB.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, AICPQMIB.RC);
        }
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-CIZCUST", CICCUST);
        CEP.TRC(SCCGWA, CICCUST.RC.RC_CODE);
        if (CICCUST.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, CICCUST.RC);
        }
    }
    public void S000_CALL_DDZSTRAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "DD-S-TR-AC", DDCSTRAC, true);
    }
    public void T000_READ_DCTDCICT() throws IOException,SQLException,Exception {
        DCTDCICT_RD = new DBParm();
        DCTDCICT_RD.TableName = "DCTDCICT";
        IBS.READ(SCCGWA, DCRDCICT, DCTDCICT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
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
    public void T000_READUPD_DCTDCICT() throws IOException,SQLException,Exception {
        DCTDCICT_RD = new DBParm();
        DCTDCICT_RD.TableName = "DCTDCICT";
        DCTDCICT_RD.upd = true;
        IBS.READ(SCCGWA, DCRDCICT, DCTDCICT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
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
    public void T000_READNEXT_DCTDCICT() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, DCRDCICT, this, DCTDCICT_BR);
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
    public void T000_WRITE_DCTDCICT() throws IOException,SQLException,Exception {
        DCTDCICT_RD = new DBParm();
        DCTDCICT_RD.TableName = "DCTDCICT";
        IBS.WRITE(SCCGWA, DCRDCICT, DCTDCICT_RD);
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
