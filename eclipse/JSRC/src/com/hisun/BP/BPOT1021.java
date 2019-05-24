package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICMSG_ERROR_MSG;
import com.hisun.CI.CICACCU;
import com.hisun.LN.LNRTRAN;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class BPOT1021 {
    boolean pgmRtn = false;
    String CPN_S_INQ_FHISD = "BP-S-INQ-FHISD";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    String WS_AC_CHNM_CN = " ";
    String WS_CI_NO = " ";
    int WS_BR_NEW_BBR = 0;
    int WS_BR_OLD_BBR = 0;
    int WS_AC_MSTBR = 0;
    int WS_TEMP_BBR = 0;
    int WS_AC_MSTBR_GEN = 0;
    BPOT1021_WS_LNCSILW WS_LNCSILW = new BPOT1021_WS_LNCSILW();
    char WS_HMIB_FLG = ' ';
    char WS_THIS_FLG = ' ';
    char WS_TLR_FLG = ' ';
    char WS_NHIST_FLG = ' ';
    char WS_FEHIS_FLG = ' ';
    char WS_LN_FLG = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    BPREVEH BPREVEH = new BPREVEH();
    BPCSIFHD BPCSIFHD = new BPCSIFHD();
    BPRTHIS BPRTHIS = new BPRTHIS();
    AIRHMIB AIRHMIB = new AIRHMIB();
    BPCO000 BPCO000 = new BPCO000();
    BPRNHIST BPRNHIST = new BPRNHIST();
    BPRFEHIS BPRFEHIS = new BPRFEHIS();
    CICACCU CICACCU = new CICACCU();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCTEVEH BPCTEVEH = new BPCTEVEH();
    BPCUCHBR BPCUCHBR = new BPCUCHBR();
    AICMSG_ERROR_MSG AICMSG_ERROR_MSG = new AICMSG_ERROR_MSG();
    BPRVCHH BPRVCHH = new BPRVCHH();
    BPCTVCHH BPCTVCHH = new BPCTVCHH();
    LNRTRAN LNRTRAN = new LNRTRAN();
    String WS_SET_NO = " ";
    short WS_SET_SEQ = 0;
    int WS_TR_DATE = 0;
    SCCGWA SCCGWA;
    BPB8030_AWA_8030 BPB8030_AWA_8030;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT1021 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "BPB8030_AWA_8030>");
        BPB8030_AWA_8030 = (BPB8030_AWA_8030) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_CHECK_FLUSHES();
        if (pgmRtn) return;
        B030_BROWSE_HIST_OR_FHIS();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "NCB0716002");
        CEP.TRC(SCCGWA, BPCSIFHD.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSIFHD.RC);
        if (!JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NORMAL)) {
            B030_BROWSE_THIS();
            if (pgmRtn) return;
            if (WS_THIS_FLG == 'N') {
                B040_BROWSE_HMIB();
                if (pgmRtn) return;
                if (WS_HMIB_FLG == 'N') {
                    B050_BROWSE_NHIST();
                    if (pgmRtn) return;
                    if (WS_NHIST_FLG == 'N') {
                        B060_BROWSE_LNHIS();
                        if (pgmRtn) return;
                        if (WS_LN_FLG == 'N') {
                            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCSIFHD.RC);
                            JIBS_tmp_str[2] = IBS.CLS2CPY(SCCGWA, BPCSIFHD.RC);
                            JIBS_tmp_str[4] = IBS.CLS2CPY(SCCGWA, BPCSIFHD.RC);
                            if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CAN_TLR_MUST_TXTLR) 
                                || JIBS_tmp_str[2].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CAN_TLR_M_IN_TXBR) 
                                || JIBS_tmp_str[4].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_CAN_TLR_MUST_TXBR)) {
                                WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSIFHD.RC);
                                CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
                            } else {
                                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_RECORD_NOTFND;
                                CEP.ERR(SCCGWA, WS_ERR_MSG, WS_FLD_NO);
                            }
                        }
                    }
                }
            }
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRFEHIS);
        BPRFEHIS.KEY.JRNNO = BPB8030_AWA_8030.JRNNO;
        BPRFEHIS.KEY.AC_DT = BPB8030_AWA_8030.STR_DT;
        CEP.TRC(SCCGWA, BPB8030_AWA_8030.JRNNO);
        CEP.TRC(SCCGWA, BPB8030_AWA_8030.STR_DT);
        T000_SELECT_BPTFEHIS_FIRST();
        if (pgmRtn) return;
    }
    public void B020_CHECK_FLUSHES() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPREVEH);
        IBS.init(SCCGWA, BPCTEVEH);
        BPCTEVEH.INFO.POINTER = BPREVEH;
        BPCTEVEH.INFO.FUNC = 'B';
        BPCTEVEH.INFO.OPT = 'S';
        BPCTEVEH.INFO.INDEX_FLG = '2';
        BPREVEH.KEY.AC_DATE = BPB8030_AWA_8030.STR_DT;
        BPREVEH.JRN_NO = BPB8030_AWA_8030.JRNNO;
        BPREVEH.KEY.SET_NO = "" + BPB8030_AWA_8030.JRNNO;
        JIBS_tmp_int = BPREVEH.KEY.SET_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BPREVEH.KEY.SET_NO = "0" + BPREVEH.KEY.SET_NO;
        if (BPREVEH.KEY.SET_NO == null) BPREVEH.KEY.SET_NO = "";
        JIBS_tmp_int = BPREVEH.KEY.SET_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BPREVEH.KEY.SET_NO += " ";
        if (BPREVEH.KEY.SET_NO.substring(11 - 1, 11 + 2 - 1).trim().length() == 0) BPREVEH.KEY.PART_NO = 0;
        else BPREVEH.KEY.PART_NO = Short.parseShort(BPREVEH.KEY.SET_NO.substring(11 - 1, 11 + 2 - 1));
        S000_CALL_BPZTEVEH();
        if (pgmRtn) return;
        BPCTEVEH.INFO.OPT = 'N';
        S000_CALL_BPZTEVEH();
        if (pgmRtn) return;
        while (BPCTEVEH.RETURN_INFO != 'N') {
            if (BPCTEVEH.RETURN_INFO == 'F') {
                if (BPB8030_AWA_8030.STR_DT < SCCGWA.COMM_AREA.AC_DATE) {
                    if (BPREVEH.CNTR_TYPE.equalsIgnoreCase("CAAC") 
                        || BPREVEH.CNTR_TYPE.equalsIgnoreCase("MMDP") 
                        || BPREVEH.CNTR_TYPE.equalsIgnoreCase("IBTD") 
                        || BPREVEH.CNTR_TYPE.equalsIgnoreCase("IBDD")) {
                        B022_CHECK_BR_REPLACE();
                        if (pgmRtn) return;
                    }
                }
            }
            BPCTEVEH.INFO.OPT = 'N';
            S000_CALL_BPZTEVEH();
            if (pgmRtn) return;
        }
        BPCTEVEH.INFO.OPT = 'E';
        S000_CALL_BPZTEVEH();
        if (pgmRtn) return;
    }
    public void B022_CHECK_BR_REPLACE() throws IOException,SQLException,Exception {
        WS_AC_MSTBR = BPREVEH.BR_OLD;
        if (WS_AC_MSTBR != 0) {
            IBS.init(SCCGWA, BPCUCHBR);
            BPCUCHBR.OLD_BR = BPREVEH.BR_OLD;
            BPCUCHBR.ORGI_FLG = '1';
            BPCUCHBR.FUNC = 'F';
            S000_CALL_BPZUCHBR();
            if (pgmRtn) return;
            if (BPCUCHBR.NEW_BR != 0) {
                WS_AC_MSTBR = BPCUCHBR.NEW_BR;
                B023_GET_CHG_BR_BBR();
                if (pgmRtn) return;
                WS_BR_NEW_BBR = WS_AC_MSTBR_GEN;
            }
        }
        if (BPREVEH.BR_OLD > BPREVEH.BR_NEW 
            && BPCUCHBR.NEW_BR != 0) {
            IBS.init(SCCGWA, BPCTVCHH);
            IBS.init(SCCGWA, BPRVCHH);
            BPCTVCHH.INFO.FUNC = 'B';
            BPCTVCHH.INFO.OPT = 'S';
            BPCTVCHH.INFO.INDEX_FLG = '2';
            BPRVCHH.KEY.AC_DATE = BPB8030_AWA_8030.STR_DT;
            BPRVCHH.JRN_NO = BPB8030_AWA_8030.JRNNO;
            BPRVCHH.KEY.SET_NO = "" + BPB8030_AWA_8030.JRNNO;
            JIBS_tmp_int = BPRVCHH.KEY.SET_NO.length();
            for (int i=0;i<12-JIBS_tmp_int;i++) BPRVCHH.KEY.SET_NO = "0" + BPRVCHH.KEY.SET_NO;
            if (BPRVCHH.KEY.SET_NO == null) BPRVCHH.KEY.SET_NO = "";
            JIBS_tmp_int = BPRVCHH.KEY.SET_NO.length();
            for (int i=0;i<12-JIBS_tmp_int;i++) BPRVCHH.KEY.SET_NO += " ";
            if (BPRVCHH.KEY.SET_NO.substring(11 - 1, 11 + 2 - 1).trim().length() == 0) BPRVCHH.KEY.PART_NO = 0;
            else BPRVCHH.KEY.PART_NO = Integer.parseInt(BPRVCHH.KEY.SET_NO.substring(11 - 1, 11 + 2 - 1));
            BPCTVCHH.INFO.POINTER = BPRVCHH;
            S000_CALL_BPZTVCHH();
            if (pgmRtn) return;
            BPCTVCHH.INFO.OPT = 'N';
            S000_CALL_BPZTVCHH();
            if (pgmRtn) return;
            while (BPCTVCHH.RETURN_INFO != 'N') {
                if (WS_BR_NEW_BBR > BPRVCHH.BR) {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_REP_BR_NO_OTHDAY_CAN);
                }
                BPCTVCHH.INFO.OPT = 'N';
                S000_CALL_BPZTVCHH();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, BPRVCHH);
            IBS.init(SCCGWA, BPCTVCHH);
            BPCTVCHH.INFO.POINTER = BPRVCHH;
            BPCTVCHH.INFO.FUNC = 'B';
            BPCTVCHH.INFO.OPT = 'E';
            S000_CALL_BPZTVCHH();
            if (pgmRtn) return;
        }
    }
    public void B023_GET_CHG_BR_BBR() throws IOException,SQLException,Exception {
        WS_AC_MSTBR_GEN = WS_AC_MSTBR;
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = WS_AC_MSTBR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCPQORG.ATTR);
        CEP.TRC(SCCGWA, BPCPQORG.BR);
        CEP.TRC(SCCGWA, BPCPQORG.BBR);
        WS_TEMP_BBR = BPCPQORG.BBR;
        if (BPCPQORG.ATTR != '2') {
            if (BPCPQORG.ATTR == '3') {
                if (BPCPQORG.BBR != 0) {
                    IBS.init(SCCGWA, BPCPQORG);
                    CEP.TRC(SCCGWA, WS_TEMP_BBR);
                    BPCPQORG.BR = WS_TEMP_BBR;
                    S000_CALL_BPZPQORG();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, BPCPQORG.ATTR);
                    CEP.TRC(SCCGWA, BPCPQORG.BBR);
                    if (BPCPQORG.ATTR != '2') {
                        CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_NOT_BOOK_BR);
                    } else {
                        WS_AC_MSTBR_GEN = BPCPQORG.BBR;
                    }
                } else {
                    CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_BR_INPUT_ERROR);
                }
            } else {
                CEP.ERR(SCCGWA, AICMSG_ERROR_MSG.AI_NOT_BOOK_BR);
            }
        }
    }
    public void B030_BROWSE_HIST_OR_FHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSIFHD);
        BPCSIFHD.INPUT.JRNNO = BPB8030_AWA_8030.JRNNO;
        BPCSIFHD.INPUT.JRN_SEQ = BPB8030_AWA_8030.JRN_SEQ;
        BPCSIFHD.INPUT.AC_DT = BPB8030_AWA_8030.STR_DT;
        CEP.TRC(SCCGWA, BPB8030_AWA_8030.JRNNO);
        CEP.TRC(SCCGWA, BPB8030_AWA_8030.JRN_SEQ);
        CEP.TRC(SCCGWA, BPB8030_AWA_8030.STR_DT);
        S000_CALL_BPZSIFHD();
        if (pgmRtn) return;
    }
    public void B030_BROWSE_THIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRTHIS);
        BPRTHIS.KEY.VCH_NO = "" + BPB8030_AWA_8030.JRNNO;
        JIBS_tmp_int = BPRTHIS.KEY.VCH_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BPRTHIS.KEY.VCH_NO = "0" + BPRTHIS.KEY.VCH_NO;
        BPRTHIS.KEY.DATE = BPB8030_AWA_8030.STR_DT;
        T000_SELECT_BPTTHIS_FIRST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "NCB0616001");
        CEP.TRC(SCCGWA, BPRTHIS.KEY.VCH_NO);
        CEP.TRC(SCCGWA, BPRTHIS.KEY.DATE);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, BPRTHIS.TLR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.TL_ID);
        if (WS_THIS_FLG == 'Y') {
            if (BPRTHIS.KEY.DATE == SCCGWA.COMM_AREA.AC_DATE) {
                if (BPRTHIS.TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                    WS_TLR_FLG = 'Y';
                } else {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAN_TLR_MUST_TXTLR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
                if (BPRTHIS.BR == SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                    WS_TLR_FLG = 'Y';
                } else {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAN_TLR_M_IN_TXBR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            R000_FORMAT_DATA1();
            if (pgmRtn) return;
        }
    }
    public void B040_BROWSE_HMIB() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, AIRHMIB);
        WS_SET_NO = "" + BPB8030_AWA_8030.JRNNO;
        JIBS_tmp_int = WS_SET_NO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) WS_SET_NO = "0" + WS_SET_NO;
        WS_TR_DATE = BPB8030_AWA_8030.STR_DT;
        CEP.TRC(SCCGWA, BPB8030_AWA_8030.JRNNO);
        CEP.TRC(SCCGWA, BPB8030_AWA_8030.JRN_SEQ);
        CEP.TRC(SCCGWA, BPB8030_AWA_8030.STR_DT);
        CEP.TRC(SCCGWA, WS_SET_NO);
        CEP.TRC(SCCGWA, WS_SET_SEQ);
        CEP.TRC(SCCGWA, WS_TR_DATE);
        T000_SELECT_AITHMIB_FIRST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, WS_HMIB_FLG);
        if (WS_HMIB_FLG == 'Y') {
            if (AIRHMIB.KEY.TR_DATE == SCCGWA.COMM_AREA.AC_DATE) {
                if (AIRHMIB.TR_TELLER.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                    WS_TLR_FLG = 'Y';
                } else {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAN_TLR_MUST_TXTLR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
                if (AIRHMIB.TR_BR == SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                    WS_TLR_FLG = 'Y';
                } else {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAN_TLR_M_IN_TXBR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            R000_FORMAT_DATA2();
            if (pgmRtn) return;
        }
    }
    public void B050_BROWSE_NHIST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRNHIST);
        BPRNHIST.KEY.JRNNO = BPB8030_AWA_8030.JRNNO;
        BPRNHIST.KEY.JRN_SEQ = BPB8030_AWA_8030.JRN_SEQ;
        BPRNHIST.KEY.AC_DT = BPB8030_AWA_8030.STR_DT;
        T000_SELECT_BPTNHIST();
        if (pgmRtn) return;
        if (WS_NHIST_FLG == 'Y') {
            if (BPRNHIST.KEY.AC_DT == SCCGWA.COMM_AREA.AC_DATE) {
                if (BPRNHIST.TX_TLR.equalsIgnoreCase(SCCGWA.COMM_AREA.TL_ID)) {
                    WS_TLR_FLG = 'Y';
                } else {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAN_TLR_MUST_TXTLR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            } else {
                if (BPRNHIST.TX_BR == SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                    WS_TLR_FLG = 'Y';
                } else {
                    WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_CAN_TLR_M_IN_TXBR;
                    S000_ERR_MSG_PROC();
                    if (pgmRtn) return;
                }
            }
            R000_FORMAT_DATA3();
            if (pgmRtn) return;
        }
    }
    public void B060_BROWSE_LNHIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, LNRTRAN);
        LNRTRAN.KEY.TR_JRN_NO = BPB8030_AWA_8030.JRNNO;
        LNRTRAN.KEY.TR_DATE = BPB8030_AWA_8030.STR_DT;
        LNTTRAN_RD = new DBParm();
        LNTTRAN_RD.TableName = "LNTTRAN";
        LNTTRAN_RD.where = "TR_JRN_NO = :LNRTRAN.KEY.TR_JRN_NO "
            + "AND TR_DATE = :LNRTRAN.KEY.TR_DATE";
        IBS.READ(SCCGWA, LNRTRAN, this, LNTTRAN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_LN_FLG = 'Y';
            R000_FORMAT_DATA4();
            if (pgmRtn) return;
        } else {
            WS_LN_FLG = 'N';
        }
        CEP.TRC(SCCGWA, LNRTRAN.TRAN_STS);
    }
    public void R000_OUTPUT_FMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = "BP000";
        SCCFMT.DATA_PTR = BPCO000;
        SCCFMT.DATA_LEN = 1325;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void R000_FORMAT_DATA1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCO000);
        BPCO000.AC_DT = BPRTHIS.KEY.DATE;
        BPCO000.TX_DT = BPRTHIS.KEY.DATE;
        if (BPRTHIS.KEY.VCH_NO.trim().length() == 0) BPCO000.JRNNO = 0;
        else BPCO000.JRNNO = Long.parseLong(BPRTHIS.KEY.VCH_NO);
        BPCO000.JRN_SEQ = (short) BPRTHIS.KEY.SEQ;
        BPCO000.VCHNO = BPRTHIS.KEY.VCH_NO;
        BPCO000.RLT_AC = BPRTHIS.AC;
        BPCO000.APP_MMO = BPRTHIS.AP_CODE;
        BPCO000.TX_CD = BPRTHIS.TR_CODE;
        if (BPRTHIS.STS == '1') {
            BPCO000.TX_STS = 'C';
        } else {
            BPCO000.TX_STS = 'N';
        }
        BPCO000.TX_CCY = BPRTHIS.CCY;
        BPCO000.TX_AMT = BPRTHIS.AMT;
        BPCO000.DRCRFLG = BPRTHIS.DC_FLG;
        BPCO000.TX_BR = BPRTHIS.BR;
        BPCO000.TX_TLR = BPRTHIS.TLR;
        BPCO000.SUP1 = BPRTHIS.SUP;
        BPCO000.REMARK = BPRTHIS.RMK;
        if (BPRTHIS.TS == null) BPRTHIS.TS = "";
        JIBS_tmp_int = BPRTHIS.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) BPRTHIS.TS += " ";
        JIBS_tmp_str[0] = "" + BPCO000.TX_TM;
        JIBS_f0 = "";
        for (int i=0;i<6-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + BPCO000.TX_TM;
        JIBS_NumStr = BPRTHIS.TS.substring(12 - 1, 12 + 2 - 1) + JIBS_NumStr.substring(2);
        BPCO000.TX_TM = Integer.parseInt(JIBS_NumStr);
        if (BPRTHIS.TS == null) BPRTHIS.TS = "";
        JIBS_tmp_int = BPRTHIS.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) BPRTHIS.TS += " ";
        JIBS_tmp_str[0] = "" + BPCO000.TX_TM;
        JIBS_f0 = "";
        for (int i=0;i<6-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + BPCO000.TX_TM;
        JIBS_NumStr = JIBS_NumStr.substring(0, 3 - 1) + BPRTHIS.TS.substring(15 - 1, 15 + 2 - 1) + JIBS_NumStr.substring(3 + 2 - 1);
        BPCO000.TX_TM = Integer.parseInt(JIBS_NumStr);
        if (BPRTHIS.TS == null) BPRTHIS.TS = "";
        JIBS_tmp_int = BPRTHIS.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) BPRTHIS.TS += " ";
        JIBS_tmp_str[0] = "" + BPCO000.TX_TM;
        JIBS_f0 = "";
        for (int i=0;i<6-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + BPCO000.TX_TM;
        JIBS_NumStr = JIBS_NumStr.substring(0, 5 - 1) + BPRTHIS.TS.substring(18 - 1, 18 + 2 - 1) + JIBS_NumStr.substring(5 + 2 - 1);
        BPCO000.TX_TM = Integer.parseInt(JIBS_NumStr);
        BPCO000.CCY_TYPE = '1';
        BPCO000.TX_VAL_DT = BPRTHIS.KEY.DATE;
        BPCO000.TX_TYPE = 'C';
        IBS.init(SCCGWA, CICACCU);
        WS_AC_CHNM_CN = " ";
        CICACCU.DATA.AGR_NO = BPRTHIS.AC;
        R000_GET_AC_NAME();
        if (pgmRtn) return;
        if (BPRTHIS.AC_NAME.trim().length() > 0) {
            BPCO000.RLT_AC_CHNM = BPRTHIS.AC_NAME;
        } else {
            BPCO000.RLT_AC_CHNM = WS_AC_CHNM_CN;
        }
        if (CICACCU.DATA.OPN_BR != 0) {
            IBS.init(SCCGWA, BPCPQORG);
            BPCPQORG.BR = CICACCU.DATA.OPN_BR;
            S000_CALL_BPZPQORG();
            if (pgmRtn) return;
            if (BPCPQORG.RC.RC_CODE == 0) {
                CEP.TRC(SCCGWA, BPCPQORG.CHN_NM);
                BPCO000.RLT_OPN_BR_CHN = BPCPQORG.CHN_NM;
            }
        }
        R000_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void R000_FORMAT_DATA2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCO000);
        BPCO000.AC_DT = AIRHMIB.KEY.TR_DATE;
        if (AIRHMIB.KEY.SET_NO.trim().length() == 0) BPCO000.JRNNO = 0;
        else BPCO000.JRNNO = Long.parseLong(AIRHMIB.KEY.SET_NO);
        BPCO000.JRN_SEQ = AIRHMIB.KEY.SET_SEQ;
        BPCO000.VCHNO = AIRHMIB.KEY.SET_NO;
        BPCO000.AC = AIRHMIB.KEY.AC;
        BPCO000.TX_CD = AIRHMIB.TR_CODE;
        BPCO000.TX_DT = AIRHMIB.KEY.TR_DATE;
        BPCO000.TX_CCY = AIRHMIB.CCY;
        BPCO000.TX_AMT = AIRHMIB.AMT;
        BPCO000.DRCRFLG = AIRHMIB.SIGN;
        BPCO000.TX_BR = AIRHMIB.TR_BR;
        BPCO000.TX_DP = (short) AIRHMIB.OWNER_BK;
        BPCO000.REMARK = AIRHMIB.DESC;
        BPCO000.TX_CHNL = AIRHMIB.CHNL_NO;
        BPCO000.TX_TLR = AIRHMIB.TR_TELLER;
        BPCO000.SUP1 = AIRHMIB.SUP_TLR1;
        BPCO000.SUP2 = AIRHMIB.SUP_TLR1;
        BPCO000.RLT_OPN_BR = "" + AIRHMIB.PAY_BR;
        JIBS_tmp_int = BPCO000.RLT_OPN_BR.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) BPCO000.RLT_OPN_BR = "0" + BPCO000.RLT_OPN_BR;
        BPCO000.RLT_OPN_BR_CHN = AIRHMIB.PAY_BKNM;
        BPCO000.RLT_AC_CHNM = AIRHMIB.PAY_MAN;
        BPCO000.RLT_AC = AIRHMIB.THEIR_AC;
        BPCO000.CUR_BAL = AIRHMIB.AFT_AMT;
        if (AIRHMIB.TS == null) AIRHMIB.TS = "";
        JIBS_tmp_int = AIRHMIB.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) AIRHMIB.TS += " ";
        JIBS_tmp_str[0] = "" + BPCO000.TX_TM;
        JIBS_f0 = "";
        for (int i=0;i<6-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + BPCO000.TX_TM;
        JIBS_NumStr = AIRHMIB.TS.substring(12 - 1, 12 + 2 - 1) + JIBS_NumStr.substring(2);
        BPCO000.TX_TM = Integer.parseInt(JIBS_NumStr);
        if (AIRHMIB.TS == null) AIRHMIB.TS = "";
        JIBS_tmp_int = AIRHMIB.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) AIRHMIB.TS += " ";
        JIBS_tmp_str[0] = "" + BPCO000.TX_TM;
        JIBS_f0 = "";
        for (int i=0;i<6-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + BPCO000.TX_TM;
        JIBS_NumStr = JIBS_NumStr.substring(0, 3 - 1) + AIRHMIB.TS.substring(15 - 1, 15 + 2 - 1) + JIBS_NumStr.substring(3 + 2 - 1);
        BPCO000.TX_TM = Integer.parseInt(JIBS_NumStr);
        if (AIRHMIB.TS == null) AIRHMIB.TS = "";
        JIBS_tmp_int = AIRHMIB.TS.length();
        for (int i=0;i<26-JIBS_tmp_int;i++) AIRHMIB.TS += " ";
        JIBS_tmp_str[0] = "" + BPCO000.TX_TM;
        JIBS_f0 = "";
        for (int i=0;i<6-JIBS_tmp_str[0].length();i++) JIBS_f0 += "0";
        JIBS_NumStr = JIBS_f0 + BPCO000.TX_TM;
        JIBS_NumStr = JIBS_NumStr.substring(0, 5 - 1) + AIRHMIB.TS.substring(18 - 1, 18 + 2 - 1) + JIBS_NumStr.substring(5 + 2 - 1);
        BPCO000.TX_TM = Integer.parseInt(JIBS_NumStr);
        BPCO000.CCY_TYPE = '1';
        BPCO000.TX_VAL_DT = AIRHMIB.KEY.TR_DATE;
        BPCO000.TX_STS = AIRHMIB.EC_IND;
        IBS.init(SCCGWA, CICACCU);
        WS_AC_CHNM_CN = " ";
        CICACCU.DATA.AGR_NO = AIRHMIB.THEIR_AC;
        R000_GET_AC_NAME();
        if (pgmRtn) return;
        BPCO000.RLT_AC_CHNM = WS_AC_CHNM_CN;
        R000_OUTPUT_FMT();
        if (pgmRtn) return;
    }
    public void R000_FORMAT_DATA3() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCO000);
        BPCO000.AC_DT = BPRNHIST.KEY.AC_DT;
        BPCO000.JRNNO = BPRNHIST.KEY.JRNNO;
        BPCO000.JRN_SEQ = BPRNHIST.KEY.JRN_SEQ;
        BPCO000.VCHNO = "" + BPRNHIST.KEY.JRNNO;
        JIBS_tmp_int = BPCO000.VCHNO.length();
        for (int i=0;i<12-JIBS_tmp_int;i++) BPCO000.VCHNO = "0" + BPCO000.VCHNO;
        BPCO000.AC = BPRNHIST.AC;
        BPCO000.TX_CD = BPRNHIST.TX_CD;
        BPCO000.TX_DT = BPRNHIST.TX_DT;
        BPCO000.TX_BR = BPRNHIST.TX_BR;
        BPCO000.TX_DP = BPRNHIST.TX_DP;
        BPCO000.REMARK = BPRNHIST.TX_RMK;
        BPCO000.TX_CHNL = BPRNHIST.TX_CHNL;
        BPCO000.TX_TLR = BPRNHIST.TX_TLR;
        BPCO000.SUP1 = BPRNHIST.SUP1;
        BPCO000.SUP2 = BPRNHIST.SUP2;
        BPCO000.TX_STS = BPRNHIST.TX_STS;
        BPCO000.TX_VAL_DT = BPRNHIST.ORG_AC_DT;
        BPCO000.TX_REV_DT = BPRNHIST.TX_REV_DT;
        BPCO000.TX_MMO = BPRNHIST.TX_TYP_CD;
        BPCO000.TX_TM = BPRNHIST.TX_TM;
        if (BPRNHIST.FMT_ID.equalsIgnoreCase("LNCSILW")) {
