package com.hisun.IB;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICPQMIB;
import com.hisun.AI.AICUUPIA;
import com.hisun.BP.BPCCINTI;
import com.hisun.BP.BPCFX;
import com.hisun.BP.BPCPFHIS;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPOEWA;
import com.hisun.BP.BPCPQORG;
import com.hisun.BP.BPCPQPRD;
import com.hisun.BP.BPCPRMR;
import com.hisun.BP.BPCRBANK;
import com.hisun.BP.BPCSOCAC;
import com.hisun.CI.CICACCU;
import com.hisun.CI.CICCUST;
import com.hisun.DD.DDCIMMST;
import com.hisun.DD.DDCMSG_ERROR_MSG;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class IBZACCL {
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    String K_IBTMST = "IBTMST  ";
    String K_IBTINSH = "IBTINSH ";
    String K_IBTSCASH = "IBTSCASH";
    String K_IBTBALF = "IBTBALF ";
    String K_IB_PROD_MODEL = "IBDD";
    String K_OUTPUT_FMT = "IBA20";
    String K_OUTPUT_FMT1 = "IBT03";
    IBZACCL_WS_AC_STATUS WS_AC_STATUS = new IBZACCL_WS_AC_STATUS();
    int WS_SEQ = 0;
    double WS_AMT = 0;
    String WS_CORP_ID = " ";
    char WS_CORP_ATTR = ' ';
    short WS_I = 0;
    double WS_DEL_AMT = 0;
    double WS_SELL_AMT = 0;
    double WS_CNT_AMT = 0;
    int WS_BR = 0;
    double WS_LOSS_AMT = 0;
    double WS_ESET_AMT = 0;
    char WS_TABLE_REC = ' ';
    char WS_TXNBR_FLAG = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    CICCUST CICCUST = new CICCUST();
    CICSACR CICSACR = new CICSACR();
    IBCQINF IBCQINF = new IBCQINF();
    IBCPMORG IBCPMORG = new IBCPMORG();
    IBCOACCL IBCOACCL = new IBCOACCL();
    IBCSETCK IBCSETCK = new IBCSETCK();
    IBCCLFHI IBCCLFHI = new IBCCLFHI();
    IBCNFHIS IBCNFHIO = new IBCNFHIS();
    IBCNFHIS IBCNFHIN = new IBCNFHIS();
    BPCRBANK BPCRBANK = new BPCRBANK();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPCPOEWA BPCPOEWA = new BPCPOEWA();
    BPCPFHIS BPCPFHIS = new BPCPFHIS();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPQPRD BPCPQPRD = new BPCPQPRD();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCFX BPCFX = new BPCFX();
    AICPQMIB AICPQMIB = new AICPQMIB();
    AICUUPIA AICUUPIA = new AICUUPIA();
    CICACCU CICACCU = new CICACCU();
    BPCSOCAC BPCSOCAC = new BPCSOCAC();
    CICSACAC CICSACAC = new CICSACAC();
    BPCCINTI BPCCINTI = new BPCCINTI();
    DDCIMMST DDCIMMST = new DDCIMMST();
    IBCPOSTA IBCPOSTA = new IBCPOSTA();
    IBCQINF IBCQINO = new IBCQINF();
    IBRMST IBRMST = new IBRMST();
    IBRSCASH IBRSCASH = new IBRSCASH();
    IBRINSH IBRINSH = new IBRINSH();
    IBRBALF IBRBALF = new IBRBALF();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBCACCL IBCACCL;
    public void MP(SCCGWA SCCGWA, IBCACCL IBCACCL) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCACCL = IBCACCL;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBZACCL return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, SCCEXCP);
        IBCACCL.RC.RC_MMO = " ";
        IBCACCL.RC.RC_CODE = 0;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B030_GET_AC_INFO_UPD();
        if (pgmRtn) return;
        B040_DEL_IBTSCASH();
        if (pgmRtn) return;
        if (WS_LOSS_AMT != 0) {
            B020_CHECK_INPUT();
            if (pgmRtn) return;
            B050_UPD_BAL();
            if (pgmRtn) return;
            B060_UPD_IBTBALF();
            if (pgmRtn) return;
            B070_PROC_VOUCHER();
            if (pgmRtn) return;
            B080_WRITE_HIST();
            if (pgmRtn) return;
        }
        B090_WRITE_NHIS();
        if (pgmRtn) return;
        B100_WRITE_IBTINSH();
        if (pgmRtn) return;
        B110_UPD_IBTMST();
        if (pgmRtn) return;
        B120_AC_CLOSE_PROC();
        if (pgmRtn) return;
        B130_UPD_BPTOCAC_PROC();
        if (pgmRtn) return;
        B140_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCACCL.CIFNO);
        if (IBCACCL.CIFNO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.CIFNO_M, IBCACCL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, IBCACCL.NOS_CD);
        CEP.TRC(SCCGWA, IBCACCL.CCY);
        CEP.TRC(SCCGWA, IBCACCL.AC_NO);
        if ((IBCACCL.NOS_CD.trim().length() == 0 
            || IBCACCL.CCY.trim().length() == 0) 
            && IBCACCL.AC_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT_ONE, IBCACCL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        B010_01_GET_AC_INFO();
        if (pgmRtn) return;
        B010_03_CHECK_CIFNO();
        if (pgmRtn) return;
        if (IBCQINF.OUTPUT_DATA.AC_ATTR == 'L') {
            B010_05_GET_CORRAC_INFO();
            if (pgmRtn) return;
        }
    }
    public void B010_01_GET_AC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCQINF);
        if (IBCACCL.AC_NO.trim().length() > 0) {
            IBCQINF.INPUT_DATA.AC_NO = IBCACCL.AC_NO;
            if (IBCACCL.AC_NO.equalsIgnoreCase(IBCACCL.OTH_AC_NO)) {
                IBS.CPY2CLS(SCCGWA, DDCMSG_ERROR_MSG.DD_AC_CAN_NOT_SAME, IBCACCL.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            IBCQINF.INPUT_DATA.NOSTRO_CD = IBCACCL.NOS_CD;
            IBCQINF.INPUT_DATA.CCY = IBCACCL.CCY;
        }
        S000_CALL_IBZQINF();
        if (pgmRtn) return;
        if ((SCCGWA.COMM_AREA.CANCEL_IND == ' ' 
            && IBCQINF.OUTPUT_DATA.AC_STS != 'N') 
            || (SCCGWA.COMM_AREA.CANCEL_IND != ' ' 
            && IBCQINF.OUTPUT_DATA.AC_STS != 'C')) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.NOT_NORMAL, IBCACCL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.AC_ATTR);
        if (IBCQINF.OUTPUT_DATA.AC_ATTR == 'L') {
            if (SCCGWA.COMM_AREA.CANCEL_IND == 'Y') {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.L_AC_NOT, IBCACCL.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.VALUE_BAL);
            CEP.TRC(SCCGWA, IBCQINF.OUTPUT_DATA.L_VALUE_BAL);
            if (IBCQINF.OUTPUT_DATA.VALUE_BAL < 0) {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.AC_OD, IBCACCL.RC);
                Z_RET();
                if (pgmRtn) return;
            } else if (IBCQINF.OUTPUT_DATA.VALUE_BAL > 0) {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.BAL_NOT_ZERO, IBCACCL.RC);
                Z_RET();
                if (pgmRtn) return;
            } else {
                if (IBCQINF.OUTPUT_DATA.L_VALUE_BAL != 0) {
                    IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.T_NOT_CLOSE, IBCACCL.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B010_02_CHECK_TXNBR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPRMR);
        IBS.init(SCCGWA, IBCPMORG);
        IBCPMORG.KEY.TYP = "PIB09";
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        IBCPMORG.KEY.CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        CEP.TRC(SCCGWA, IBCPMORG.KEY.CD);
        BPCPRMR.DAT_PTR = IBCPMORG;
        S000_CALL_BPZPRMR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, IBCPMORG.DATA_TXT.CTL_TYP);
        if (IBCPMORG.DATA_TXT.CTL_TYP == '0') {
            if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != IBCQINF.OUTPUT_DATA.POST_ACT_CTR) {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.TXN_BR, IBCACCL.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else if (IBCPMORG.DATA_TXT.CTL_TYP == '1') {
            for (WS_I = 1; WS_TXNBR_FLAG != 'Y' 
                && WS_I <= 10; WS_I += 1) {
                CEP.TRC(SCCGWA, IBCPMORG.DATA_TXT.BR[WS_I-1]);
                if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == IBCPMORG.DATA_TXT.BR[WS_I-1]) {
                    WS_TXNBR_FLAG = 'Y';
                }
            }
            CEP.TRC(SCCGWA, WS_TXNBR_FLAG);
            if (WS_TXNBR_FLAG != 'Y') {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.TXN_BR, IBCACCL.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
        }
    }
    public void B010_03_CHECK_CIFNO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = IBCQINF.OUTPUT_DATA.CI_NO;
        CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
        CICCUST.FUNC = 'C';
        S000_LINK_CIZCUST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICCUST.RC.RC_CODE);
        if (CICCUST.RC.RC_CODE == 3011) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.CIFNO_NOEXIST, IBCACCL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, CICCUST.O_DATA.O_CI_TYP);
        if (CICCUST.O_DATA.O_CI_TYP != '3') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.CIF_NOFI, IBCACCL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_04_SETT_AMT_CHECK() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCACCL.ASET_AMT);
        CEP.TRC(SCCGWA, IBCACCL.ESET_AMT);
        WS_ESET_AMT = IBCACCL.ASET_AMT * 1;
        bigD = new BigDecimal(WS_ESET_AMT);
        WS_ESET_AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
        CEP.TRC(SCCGWA, WS_ESET_AMT);
        if (IBCACCL.ASET_AMT != WS_ESET_AMT) {
            WS_DEL_AMT = IBCACCL.ASET_AMT - WS_ESET_AMT;
            IBS.init(SCCGWA, BPCPRMR);
            IBS.init(SCCGWA, IBCSETCK);
            IBCSETCK.KEY.TYP = "PIB01";
            IBCSETCK.KEY.CD = "DIFCK";
            BPCPRMR.DAT_PTR = IBCSETCK;
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
            WS_CNT_AMT = IBCSETCK.DATA_TXT.CNT_AMT;
            if (IBCACCL.CCY.equalsIgnoreCase("156")) {
                WS_SELL_AMT = WS_DEL_AMT;
            } else {
                IBS.init(SCCGWA, BPCFX);
                BPCFX.FUNC = '3';
                BPCFX.CHNL = SCCGWA.COMM_AREA.CHNL;
                BPCFX.EXR_TYPE = "MDR";
                BPCFX.BR = IBCQINF.OUTPUT_DATA.POST_ACT_CTR;
                BPCFX.BUY_CCY = IBCACCL.CCY;
                BPCFX.BUY_AMT = WS_DEL_AMT;
                BPCFX.SELL_CCY = "156";
                S000_CALL_BPZSFX();
                if (pgmRtn) return;
                WS_SELL_AMT = BPCFX.SELL_AMT;
            }
            CEP.TRC(SCCGWA, WS_SELL_AMT);
            if (WS_SELL_AMT >= WS_CNT_AMT) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.ASAMT_THAN_ESAMT_MOR);
            }
        }
    }
    public void B010_05_GET_CORRAC_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCIMMST);
        DDCIMMST.DATA.KEY.AC_NO = IBCQINF.OUTPUT_DATA.CORRAC_NO;
        DDCIMMST.TX_TYPE = 'I';
        S000_CALL_DDZIMMST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, DDCIMMST.DATA.AC_STS);
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
            if (DDCIMMST.DATA.AC_STS != 'C') {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.L_STS, IBCACCL.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        } else {
            if (DDCIMMST.DATA.AC_STS == 'C') {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.L_CLOSED, IBCACCL.RC);
                Z_RET();
                if (pgmRtn) return;
            }
        }
    }
    public void B020_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCACCL.APRIN_AMT);
        CEP.TRC(SCCGWA, IBCACCL.ASET_AMT);
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ' 
            && IBCACCL.APRIN_AMT != IBCQINF.OUTPUT_DATA.VALUE_BAL) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.DIFF_BAL_AMT, IBCACCL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, IBCACCL.OTH_AC_ATTR);
        if (IBCACCL.OTH_AC_ATTR == ' ') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.OTH_AC_ATTR_M, IBCACCL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, IBCACCL.OTH_AC_NO);
        if (IBCACCL.OTH_AC_NO.trim().length() == 0) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.OTH_AC_NO_M, IBCACCL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, IBCACCL.OTH_AC_ATTR);
        if (IBCACCL.OTH_AC_ATTR == 'N') {
            IBS.init(SCCGWA, IBCQINO);
            IBCQINO.INPUT_DATA.AC_NO = IBCACCL.OTH_AC_NO;
            S000_CALL_IBZQINO();
            if (pgmRtn) return;
            WS_BR = IBCQINO.OUTPUT_DATA.POST_ACT_CTR;
        } else {
            IBS.init(SCCGWA, AICPQMIB);
            AICPQMIB.INPUT_DATA.GL_BOOK = "BK001";
            AICPQMIB.INPUT_DATA.AC = IBCACCL.OTH_AC_NO;
            S000_CALL_AIZPQMIB();
            if (pgmRtn) return;
            WS_BR = AICPQMIB.INPUT_DATA.BR;
        }
    }
    public void B030_GET_AC_INFO_UPD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRMST);
        IBRMST.KEY.AC_NO = IBCACCL.AC_NO;
        T000_READ_UPD_IBTMST();
        if (pgmRtn) return;
        if (WS_TABLE_REC == 'N') {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.AC_NOEXIST, IBCACCL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        WS_LOSS_AMT = IBCACCL.APRIN_AMT + IBCACCL.ASET_AMT + IBCACCL.ESET_AMT;
        CEP.TRC(SCCGWA, WS_LOSS_AMT);
    }
    public void B040_DEL_IBTSCASH() throws IOException,SQLException,Exception {
        if (IBCQINF.OUTPUT_DATA.AC_ATTR == 'L') {
            IBS.init(SCCGWA, IBRSCASH);
            IBRSCASH.AC_NO = IBCACCL.AC_NO;
            CEP.TRC(SCCGWA, IBRSCASH.AC_NO);
            T000_STARTBR_UPD_IBTSCASH();
            if (pgmRtn) return;
            T000_READNEXT_IBTSCASH();
            if (pgmRtn) return;
            if (WS_TABLE_REC == 'N') {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.REC_NOTFND, IBCACCL.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            CEP.TRC(SCCGWA, IBRSCASH.TXN_AMT);
            if (IBRSCASH.TXN_AMT != 0) {
                IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.TRN_AMT, IBCACCL.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            T000_DELETE_IBTSCASH();
            if (pgmRtn) return;
        }
    }
    public void B050_UPD_BAL() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
            IBRMST.VALUE_BAL = IBRMST.VALUE_BAL - IBCACCL.APRIN_AMT;
            IBRMST.BUD_INT = IBRMST.BUD_INT - IBCACCL.ESET_AMT;
        } else {
            IBRMST.VALUE_BAL = IBRMST.VALUE_BAL + IBCACCL.APRIN_AMT;
            IBRMST.BUD_INT = IBRMST.BUD_INT + IBCACCL.ESET_AMT;
        }
    }
    public void B060_UPD_IBTBALF() throws IOException,SQLException,Exception {
        if (IBCACCL.APRIN_AMT != 0) {
            IBS.init(SCCGWA, IBRBALF);
            IBRBALF.KEY.AC_NO = IBRMST.KEY.AC_NO;
            IBRBALF.KEY.BAL_DATE = IBCACCL.CLOSE_DATE;
            T000_READ_IBTBALF_UPD();
            if (pgmRtn) return;
            if (WS_TABLE_REC == 'Y') {
                IBRBALF.BAL = IBRMST.VALUE_BAL;
                IBRBALF.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                IBRBALF.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
                IBRBALF.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_REWRITE_IBTBALF();
                if (pgmRtn) return;
            } else {
                IBRBALF.BAL = IBRMST.VALUE_BAL;
                IBRBALF.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                IBRBALF.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                IBRBALF.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                IBRBALF.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
                IBRBALF.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                T000_WRITE_IBTBALF();
                if (pgmRtn) return;
            }
        }
    }
    public void B070_PROC_VOUCHER() throws IOException,SQLException,Exception {
        if ((IBCACCL.APRIN_AMT != 0 
            || IBCACCL.ESET_AMT != 0 
            || IBCACCL.ASET_AMT != 0)) {
            B070_01_GET_PROD_CODE();
            if (pgmRtn) return;
            IBS.init(SCCGWA, BPCPOEWA);
            BPCPOEWA.DATA.CNTR_TYPE = BPCPQPRD.PRDT_MODEL;
            BPCPOEWA.DATA.PROD_CODE = IBRMST.PROD_CD;
            BPCPOEWA.DATA.EVENT_CODE = "CR";
            BPCPOEWA.DATA.BR_OLD = IBRMST.POST_CTR;
            BPCPOEWA.DATA.BR_NEW = IBRMST.POST_CTR;
            BPCPOEWA.DATA.CI_NO = IBCQINF.OUTPUT_DATA.CI_NO;
            BPCPOEWA.DATA.AC_NO = IBRMST.ACO_AC;
            BPCPOEWA.DATA.CCY_INFO[01-1].CCY = IBCACCL.CCY;
            BPCPOEWA.DATA.AMT_INFO[01-1].AMT = IBCACCL.APRIN_AMT;
            BPCPOEWA.DATA.AMT_INFO[2-1].AMT = IBCACCL.ESET_AMT * 1;
            bigD = new BigDecimal(BPCPOEWA.DATA.AMT_INFO[2-1].AMT);
            BPCPOEWA.DATA.AMT_INFO[2-1].AMT = bigD.setScale(5, RoundingMode.HALF_UP).doubleValue();
            BPCPOEWA.DATA.AMT_INFO[04-1].AMT = IBCACCL.ASET_AMT;
            BPCPOEWA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
            BPCPOEWA.DATA.POST_NARR = "IBDD AC CLOSE";
            BPCPOEWA.DATA.DESC = "IBDD AC CLOSE";
            S000_CALL_BPZPOEWA();
            if (pgmRtn) return;
            if (IBCACCL.OTH_AC_ATTR == 'N') {
                B070_03_PROC_IB_DEP();
                if (pgmRtn) return;
            } else {
                B070_02_PROC_VOUCHER();
                if (pgmRtn) return;
            }
        }
    }
    public void B070_01_GET_PROD_CODE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQPRD);
        BPCPQPRD.PRDT_CODE = IBCQINF.OUTPUT_DATA.PROD_CD;
        S000_CALL_BPZPQPRD();
        if (pgmRtn) return;
        if (!BPCPQPRD.PRDT_MODEL.equalsIgnoreCase(K_IB_PROD_MODEL)) {
            IBS.CPY2CLS(SCCGWA, IBCMSG_ERROR_MSG.NOT_IB_PROD_TYP, IBCACCL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B070_02_PROC_VOUCHER() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AICUUPIA);
        AICUUPIA.DATA.EVENT_CODE = "DR";
        AICUUPIA.DATA.AC_NO = IBCACCL.OTH_AC_NO;
        AICUUPIA.DATA.CCY = IBCACCL.CCY;
        AICUUPIA.DATA.AMT = IBCACCL.APRIN_AMT + IBCACCL.ASET_AMT;
        AICUUPIA.DATA.VALUE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.RVS_NO = IBCACCL.SUSP_SEQ;
        AICUUPIA.DATA.PROD_CODE = IBCQINF.OUTPUT_DATA.PROD_CD;
        AICUUPIA.DATA.CI_NO = IBCQINF.OUTPUT_DATA.CI_NO;
        AICUUPIA.DATA.THEIR_AC = IBCACCL.AC_NO;
        AICUUPIA.DATA.SETTLE_DT = SCCGWA.COMM_AREA.AC_DATE;
        AICUUPIA.DATA.THEIR_CCY = IBCACCL.CCY;
        AICUUPIA.DATA.THEIR_AMT = AICUUPIA.DATA.AMT;
        AICUUPIA.DATA.BR_OLD = IBCQINF.OUTPUT_DATA.POST_ACT_CTR;
        AICUUPIA.DATA.BR_NEW = IBCQINF.OUTPUT_DATA.POST_ACT_CTR;
        AICUUPIA.DATA.POST_NARR = "IBDD AC CLOSE";
        AICUUPIA.DATA.DESC = "IBDD AC CLOSE";
        S000_CALL_AIZUUPIA();
        if (pgmRtn) return;
    }
    public void B070_03_PROC_IB_DEP() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCPOSTA);
        IBCPOSTA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        IBCPOSTA.CCY = IBCACCL.CCY;
        IBCPOSTA.SIGN = 'D';
        IBCPOSTA.AMT = IBCACCL.APRIN_AMT + IBCACCL.ASET_AMT;
        IBCPOSTA.VAL_DATE = IBCACCL.CLOSE_DATE;
        IBCPOSTA.ENTRY_FLG = '1';
        IBCPOSTA.OTH_AC_NO = IBCACCL.AC_NO;
        CEP.TRC(SCCGWA, IBCPOSTA.OTH_AC_NO);
        IBCPOSTA.OTH_AC_TYPE = IBCACCL.OTH_AC_ATTR;
        IBCPOSTA.AC = IBCACCL.OTH_AC_NO;
        CEP.TRC(SCCGWA, IBCPOSTA.AC);
        IBCPOSTA.OUR_REF = " ";
        IBCPOSTA.THR_REF = " ";
        IBCPOSTA.TX_MMO = "A801";
        IBCPOSTA.NARR = " ";
        S000_CALL_IBZDRAC();
        if (pgmRtn) return;
    }
    public void B080_WRITE_HIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICACCU);
        CICACCU.DATA.AGR_NO = IBCACCL.AC_NO;
        S000_CALL_CIZACCU();
        if (pgmRtn) return;
        IBS.init(SCCGWA, IBCCLFHI);
        IBCCLFHI.AC_NO = IBCACCL.AC_NO;
        IBCCLFHI.NOSTRO_CD = IBCACCL.NOS_CD;
        IBCCLFHI.CCY = IBCACCL.CCY;
        IBCCLFHI.CUSTNME = IBCQINF.OUTPUT_DATA.AC_CHN_NAME;
        IBCCLFHI.CLOSE_DT = SCCGWA.COMM_AREA.AC_DATE;
        IBCCLFHI.VALUE_BAL = IBCQINF.OUTPUT_DATA.VALUE_BAL;
        IBCCLFHI.APRIN_AMT = IBCACCL.APRIN_AMT;
        IBCCLFHI.ESET_AMT = IBCACCL.ESET_AMT;
        IBCCLFHI.ASET_AMT = IBCACCL.ASET_AMT;
        IBCCLFHI.OTH_AC_ATTR = IBCACCL.OTH_AC_ATTR;
        IBCCLFHI.OTH_AC_NO = IBCACCL.OTH_AC_NO;
        IBCCLFHI.OTH_CUSTNME = IBCACCL.OTH_CUSTNME;
        IBCCLFHI.SUSP_SEQ = IBCACCL.SUSP_SEQ;
        IBCCLFHI.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        IBCCLFHI.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBS.init(SCCGWA, BPCPFHIS);
        BPCPFHIS.DATA.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPFHIS.DATA.VCHNO = SCCGWA.COMM_AREA.VCH_NO;
        BPCPFHIS.DATA.PRINT_IND = 'Y';
        BPCPFHIS.DATA.CI_NO = IBCQINF.OUTPUT_DATA.CI_NO;
        BPCPFHIS.DATA.AC = IBRMST.KEY.AC_NO;
        BPCPFHIS.DATA.ACO_AC = IBRMST.ACO_AC;
        BPCPFHIS.DATA.TX_CCY = IBCACCL.CCY;
        if (IBCACCL.CCY.equalsIgnoreCase("156")) {
            BPCPFHIS.DATA.TX_CCY_TYP = '1';
        } else {
            BPCPFHIS.DATA.TX_CCY_TYP = '2';
        }
        BPCPFHIS.DATA.TX_TYPE = 'T';
        BPCPFHIS.DATA.TX_AMT = IBCACCL.APRIN_AMT;
        BPCPFHIS.DATA.TX_VAL_DT = IBCACCL.CLOSE_DATE;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.TX_VAL_DT);
        BPCPFHIS.DATA.PROD_CD = IBRMST.PROD_CD;
        BPCPFHIS.DATA.PRDMO_CD = BPCPQPRD.PRDT_MODEL;
        BPCPFHIS.DATA.TX_MMO = "A800";
        BPCPFHIS.DATA.CI_NO = CICACCU.DATA.CI_NO;
        BPCPFHIS.DATA.TX_TOOL = IBRMST.KEY.AC_NO;
        BPCPFHIS.DATA.OTH_AC = IBCACCL.OTH_AC_NO;
        BPCPFHIS.DATA.RLT_AC = IBCACCL.OTH_AC_NO;
        if (IBCACCL.OTH_AC_ATTR == 'N') {
            BPCPFHIS.DATA.RLT_AC_NAME = IBCQINO.OUTPUT_DATA.AC_CHN_NAME;
        } else {
            BPCPFHIS.DATA.RLT_AC_NAME = AICPQMIB.OUTPUT_DATA.CHS_NM;
        }
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_AC_NAME);
        BPCPFHIS.DATA.RLT_BANK = "" + WS_BR;
        JIBS_tmp_int = BPCPFHIS.DATA.RLT_BANK.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCPFHIS.DATA.RLT_BANK = "0" + BPCPFHIS.DATA.RLT_BANK;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_BANK);
        B080_01_WRITE_HIST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPFHIS.DATA.RLT_BK_NM);
        BPCPFHIS.DATA.SMS_FLG = 'N';
        BPCPFHIS.DATA.VAL_BAL = IBCQINF.OUTPUT_DATA.VALUE_BAL;
        BPCPFHIS.DATA.VAL_BAL_CCY = IBCACCL.CCY;
        BPCPFHIS.DATA.TX_DRCR_FLG = 'C';
        if (SCCGWA.COMM_AREA.REVERSAL_IND == 'Y') {
            BPCPFHIS.DATA.TX_REV_DT = SCCGWA.COMM_AREA.AC_DATE;
        }
        BPCPFHIS.DATA.FMT_ID = "IBCCLFHI";
        BPCPFHIS.DATA.FMT_CODE = K_OUTPUT_FMT1;
        BPCPFHIS.DATA.FMT_LEN = 703;
        BPCPFHIS.DATA.FMT_DATA = IBS.CLS2CPY(SCCGWA, IBCCLFHI);
        S000_CALL_BPZPFHIS();
        if (pgmRtn) return;
    }
    public void B080_01_WRITE_HIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        BPCPFHIS.DATA.RLT_BK_NM = BPCPQORG.CHN_NM;
    }
    public void B090_WRITE_NHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCNFHIO);
        IBCNFHIO.NOSTRO_CD = IBCQINF.INPUT_DATA.NOSTRO_CD;
        IBCNFHIO.CCY = IBCQINF.INPUT_DATA.CCY;
        IBCNFHIO.AC_NO = IBCQINF.INPUT_DATA.AC_NO;
        IBCNFHIO.CLOSE_DATE = IBCQINF.OUTPUT_DATA.CLOSE_DATE;
        IBS.init(SCCGWA, IBCNFHIN);
        IBCNFHIN.NOSTRO_CD = IBCACCL.NOS_CD;
        IBCNFHIN.CCY = IBCACCL.CCY;
        IBCNFHIN.AC_NO = IBCACCL.AC_NO;
        IBCNFHIN.CLOSE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.AC = IBRMST.KEY.AC_NO;
        BPCPNHIS.INFO.CI_NO = IBCQINF.OUTPUT_DATA.CI_NO;
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TYP_CD = "A800";
        BPCPNHIS.INFO.CCY = IBCACCL.CCY;
        if (IBCACCL.CCY.equalsIgnoreCase("156")) {
            BPCPNHIS.INFO.CCY_TYPE = '1';
        } else {
            BPCPNHIS.INFO.CCY_TYPE = '2';
        }
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.FMT_ID = "IBCCNFHI";
        BPCPNHIS.INFO.FMT_ID_LEN = 468;
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.OLD_DAT_PT = IBCNFHIO;
        BPCPNHIS.INFO.NEW_DAT_PT = IBCNFHIN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void B100_WRITE_IBTINSH() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRINSH);
        IBRINSH.KEY.AC_NO = IBCACCL.AC_NO;
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
            CEP.TRC(SCCGWA, "111");
            WS_SEQ = 0;
            IBRINSH.KEY.DEAL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            T000_READ_IBTINSH_FIRST();
            if (pgmRtn) return;
            IBRINSH.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
            IBRINSH.KEY.SEQ = WS_SEQ;
            IBRINSH.INTS_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBRINSH.ESET_AMT = IBCACCL.ESET_AMT;
            IBRINSH.ASET_AMT = IBCACCL.ASET_AMT;
            IBRINSH.SETT_TYPE = '2';
            IBRINSH.SETT_AC_NO = IBCACCL.OTH_AC_NO;
            IBRINSH.SETT_AC_TYPE = IBCACCL.OTH_AC_ATTR;
            IBRINSH.REV_FLG = 'N';
            IBRINSH.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            IBRINSH.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBRINSH.AUTH_TLR = SCCGWA.COMM_AREA.SUP1_ID;
            IBRINSH.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            IBRINSH.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBRINSH.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
            T000_WRITE_IBTINSH();
            if (pgmRtn) return;
        } else {
            CEP.TRC(SCCGWA, "222");
            T000_READ_IBTINSH_FIRST1();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, IBRINSH.KEY.AC_NO);
            CEP.TRC(SCCGWA, IBRINSH.KEY.SEQ);
            CEP.TRC(SCCGWA, IBRINSH.KEY.DEAL_DATE);
            T000_READUPD_IBTINSH();
            if (pgmRtn) return;
            if (WS_TABLE_REC == 'Y') {
                CEP.TRC(SCCGWA, "333");
                IBRINSH.REV_FLG = 'R';
                IBRINSH.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                IBRINSH.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                IBRINSH.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
                T000_REWRITE_IBTINSH();
                if (pgmRtn) return;
            }
        }
    }
    public void B110_UPD_IBTMST() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
            IBRMST.AC_STS = 'C';
            WS_AC_STATUS.WS_STS_NORMA = '0';
            WS_AC_STATUS.WS_STS_BLOCK = '0';
            WS_AC_STATUS.WS_STS_LHOLD = '0';
            WS_AC_STATUS.WS_STS_CLOSD = '1';
            IBRMST.AC_STS_WORD = IBS.CLS2CPY(SCCGWA, WS_AC_STATUS);
            IBRMST.CLOS_DATE = SCCGWA.COMM_AREA.AC_DATE;
            IBRMST.CLOS_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            IBRMST.CLOS_TLR = SCCGWA.COMM_AREA.TL_ID;
        } else {
            IBRMST.AC_STS = 'N';
            WS_AC_STATUS.WS_STS_NORMA = '1';
            WS_AC_STATUS.WS_STS_BLOCK = '0';
            WS_AC_STATUS.WS_STS_LHOLD = '0';
            WS_AC_STATUS.WS_STS_CLOSD = '0';
            IBRMST.AC_STS_WORD = IBS.CLS2CPY(SCCGWA, WS_AC_STATUS);
            IBRMST.CLOS_DATE = 99991231;
            IBRMST.CLOS_BR = 0;
            IBRMST.CLOS_TLR = " ";
        }
        IBRMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
        IBRMST.UPD_TIME = SCCGWA.COMM_AREA.TR_TIME;
        IBRMST.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_IBTMST();
        if (pgmRtn) return;
    }
    public void B120_AC_CLOSE_PROC() throws IOException,SQLException,Exception {
        if (SCCGWA.COMM_AREA.CANCEL_IND == ' ') {
            B120_02_ACO_CLOSE();
            if (pgmRtn) return;
            B120_01_AC_CLOSE();
            if (pgmRtn) return;
        } else {
            B120_01_AC_CLOSE();
            if (pgmRtn) return;
            B120_02_ACO_CLOSE();
            if (pgmRtn) return;
        }
    }
    public void B120_01_AC_CLOSE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACR);
        CICSACR.FUNC = 'D';
        CICSACR.DATA.ENTY_TYP = '1';
        CICSACR.DATA.AGR_NO = IBCACCL.AC_NO;
        S000_LINK_CIZSACR();
        if (pgmRtn) return;
    }
    public void B120_02_ACO_CLOSE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICSACAC);
        CICSACAC.FUNC = 'D';
        CICSACAC.DATA.ACAC_NO = IBRMST.ACO_AC;
        S000_CALL_CIZSACAC();
        if (pgmRtn) return;
    }
    public void B130_UPD_BPTOCAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSOCAC);
        BPCSOCAC.FUNC = 'U';
        BPCSOCAC.AC = IBRMST.KEY.AC_NO;
        BPCSOCAC.ACO_AC = IBRMST.ACO_AC;
        BPCSOCAC.STS = 'C';
        BPCSOCAC.WORK_TYP = "24";
        BPCSOCAC.CI_TYPE = '3';
        BPCSOCAC.LOSS_AMT = IBCACCL.APRIN_AMT + IBCACCL.ASET_AMT;
        BPCSOCAC.LOSS_INT = IBCACCL.ASET_AMT;
        CEP.TRC(SCCGWA, IBCACCL.ASET_AMT);
        CEP.TRC(SCCGWA, BPCSOCAC.LOSS_INT);
        if (IBRMST.RATE_MTH == '1') {
            BPCSOCAC.LOSS_RAT = IBRMST.RATE;
        } else {
            B130_01_GET_RATE();
            if (pgmRtn) return;
            if (IBRMST.RATE_SPREAD != 0) {
                BPCSOCAC.LOSS_RAT = BPCCINTI.BASE_INFO.RATE + IBRMST.RATE_SPREAD;
            } else {
                BPCSOCAC.LOSS_RAT = BPCCINTI.BASE_INFO.RATE * ( 1 + IBRMST.RATE_PCT / 100 );
            }
        }
        CEP.TRC(SCCGWA, BPCSOCAC.LOSS_AMT);
        CEP.TRC(SCCGWA, BPCSOCAC.LOSS_RAT);
        BPCSOCAC.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
        BPCSOCAC.ID_NO = CICCUST.O_DATA.O_ID_NO;
        BPCSOCAC.CI_CNM = CICCUST.O_DATA.O_CI_NM;
        BPCSOCAC.CCY = IBRMST.CCY;
        if (IBRMST.CCY.equalsIgnoreCase("156")) {
            BPCSOCAC.CCY_TYPE = '1';
        } else {
            BPCSOCAC.CCY_TYPE = '2';
        }
        BPCSOCAC.AUT_TLR = SCCGWA.COMM_AREA.SUP1_ID;
        BPCSOCAC.CLOSE_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSOCAC.CLOSE_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCSOCAC.LOSS_INT = IBCACCL.ASET_AMT;
        BPCSOCAC.PROD_CD = IBRMST.PROD_CD;
        BPCSOCAC.REMARK = "IBDD ACCOUNT CLOSED";
        S000_CALL_BPZSOCAC();
        if (pgmRtn) return;
    }
    public void B130_01_GET_RATE() throws IOException,SQLException,Exception {
        if (IBRMST.RATE_MTH == '2') {
            IBS.init(SCCGWA, BPCCINTI);
            BPCCINTI.FUNC = 'I';
            BPCCINTI.BASE_INFO.BR = IBRMST.POST_CTR;
            BPCCINTI.BASE_INFO.CCY = IBRMST.CCY;
            BPCCINTI.BASE_INFO.BASE_TYP = IBRMST.RATE_TYPE;
            BPCCINTI.BASE_INFO.TENOR = IBRMST.RATE_TERM;
            BPCCINTI.BASE_INFO.DT = SCCGWA.COMM_AREA.AC_DATE;
            S000_CALL_BPZCINTI();
            if (pgmRtn) return;
        }
    }
    public void B140_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCOACCL);
        IBCOACCL.AC_NO = IBCQINF.INPUT_DATA.AC_NO;
        IBCOACCL.NOSTRO_CODE = IBCQINF.INPUT_DATA.NOSTRO_CD;
        IBCOACCL.CCY = IBCQINF.INPUT_DATA.CCY;
        IBCOACCL.CUSTNME = IBCQINF.OUTPUT_DATA.AC_CHN_NAME;
        IBCOACCL.VALUE_BAL = IBRMST.VALUE_BAL;
        IBCOACCL.BUD_INT = IBRMST.BUD_INT;
        IBCOACCL.APRIN_AMT = IBCACCL.APRIN_AMT;
        IBCOACCL.ASET_AMT = IBCACCL.ASET_AMT;
        IBCOACCL.CLOSE_DATE = IBRMST.CLOS_DATE;
        IBCOACCL.OTH_AC_ATTR = IBCACCL.OTH_AC_ATTR;
        IBCOACCL.OTH_AC_NO = IBCACCL.OTH_AC_NO;
        IBCOACCL.SUSP_SEQ = IBCACCL.SUSP_SEQ;
        IBCOACCL.OIC_NO = IBCQINF.OUTPUT_DATA.OIC_NO;
        IBCOACCL.RESP_CD = IBCQINF.OUTPUT_DATA.RESP_CD;
        IBCOACCL.SUB_DP = IBCQINF.OUTPUT_DATA.SUB_DP;
        IBCOACCL.AUTH_TLR = IBRMST.AUTH_TLR;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = IBCOACCL;
        SCCFMT.DATA_LEN = 476;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void S000_LINK_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
        if (CICCUST.RC.RC_CODE != 0 
            && CICCUST.RC.RC_CODE != 3011) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICCUST.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCACCL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_LINK_CIZSACR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-MAIN-CIZSACR", CICSACR);
        if (CICSACR.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, CICSACR.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCACCL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZPOEWA() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-WRT-ONL-EWA", BPCPOEWA);
        if (BPCPOEWA.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPOEWA.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCACCL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZSFX() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-FX", BPCFX);
        if (BPCFX.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFX.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCACCL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF);
        if (IBCQINF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINF.RC);
            IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[0], IBCACCL.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_IBZQINO() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINO);
        if (IBCQINO.RC.RC_CODE != 0) {
