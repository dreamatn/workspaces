package com.hisun.BP;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class BPOT3130 {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "BP168";
    int K_MAX_PAR_CNT = 99;
    char K_STSW_FLG_Y = '1';
    String CPN_F_TLR_INF_QUERY = "BP-F-TLR-INF-QUERY";
    String CPN_S_TLR_BV_CHK = "BP-S-TLR-BV-CHK     ";
    String CPN_F_TLR_BV_CHK = "BP-F-TLR-BV-CHK     ";
    String CPN_REC_NHIS = "BP-REC-NHIS         ";
    String TBL_BPTTBVD = "BPTTBVD ";
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    int WS_I = 0;
    int WS_CNT = 0;
    long WS_JRNNO = 0;
    char WS_TBL_FLAG = ' ';
    int WS_BV_NUM = 0;
    String WS_BV_CODE = " ";
    String BPOT3130_FILLER1 = "VB FLAG:";
    char WS_HIS_VB_FLG_CN = ' ';
    String BPOT3130_FILLER3 = "BV CODE:";
    String WS_HIS_BV_CODE1_CN = " ";
    String BPOT3130_FILLER5 = "STS:";
    double WS_HIS_STS1_CN = 0;
    String BPOT3130_FILLER7 = "NUM:";
    int WS_HIS_NUM1_CN = 0;
    String BPOT3130_FILLER9 = "BV CODE:";
    String WS_HIS_BV_CODE2_CN = " ";
    String BPOT3130_FILLER11 = "STS:";
    double WS_HIS_STS2_CN = 0;
    String BPOT3130_FILLER13 = "NUM:";
    int WS_HIS_NUM2_CN = 0;
    String BPOT3130_FILLER15 = "CK TYPE:";
    char WS_HIS_CK_TYP = ' ';
    String BPOT3130_FILLER17 = "REP TLR:";
    String WS_HIS_REP_TLR = " ";
    String BPOT3130_FILLER19 = "CHK TYP:";
    char WS_HIS_CHK_TYPE = ' ';
    char WS_STS = ' ';
    char WS_MID_END_STS = ' ';
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCFTLRQ BPCFTLRQ = new BPCFTLRQ();
    BPCFTBVC BPCFTBVC = new BPCFTBVC();
    BPCSTBVC BPCSTBVC = new BPCSTBVC();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCSINVT BPCSINVT = new BPCSINVT();
    BPCTINVT BPCTINVT = new BPCTINVT();
    BPRVHPB BPRVHPB = new BPRVHPB();
    BPRTBVD BPRTBVD = new BPRTBVD();
    BPRBVLT BPRBVLT = new BPRBVLT();
    BPRINVT BPRINVT = new BPRINVT();
    BPRBMOV BPRBMOV = new BPRBMOV();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    BPC3130 BPC3130;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPOT3130 return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        BPC3130 = new BPC3130();
        IBS.init(SCCGWA, BPC3130);
        IBS.CPY2CLS(SCCGWA, GWA_SC_AREA.INP_OUTP_AREA.INP_AREA_PTR, BPC3130);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPC3130.CK_TYP);
        CEP.TRC(SCCGWA, BPC3130.REP_TLR);
        CEP.TRC(SCCGWA, BPC3130.CHK_TYP);
        if (SCCGWA.COMM_AREA.TR_BANK.equalsIgnoreCase("043")) {
            if (BPC3130.CHK_TYP == '3') {
                B030_KEEPING_CARD_NHIS();
                if (pgmRtn) return;
            } else {
                if (BPC3130.CK_TYP == '0' 
                    || BPC3130.CK_TYP == '1') {
                    WS_MID_END_STS = '1';
                } else {
                    WS_MID_END_STS = '2';
                }
                if (WS_MID_END_STS == '1' 
                    || BPC3130.CK_TYP == '1') {
                    B010_CHECK_INPUT_CN();
                    if (pgmRtn) return;
                    B030_MODIFY_INVT_FOR_CN();
                    if (pgmRtn) return;
                }
                if (WS_MID_END_STS == '2' 
                    || BPC3130.CK_TYP == '2') {
                    B110_BROWSE_PROCESS();
                    if (pgmRtn) return;
                    WS_CNT = WS_CNT - 1;
                    CEP.TRC(SCCGWA, WS_CNT);
                    CEP.TRC(SCCGWA, "WS-CNT001");
                    if (WS_CNT < 1) {
                        CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_MID_DAY_LT_TWICE);
                    }
                    B010_CHECK_INPUT_CN();
                    if (pgmRtn) return;
                    B030_CHECK_ONWAY_FOR_CN();
                    if (pgmRtn) return;
                    B040_CHECK_BVLT_CN();
                    if (pgmRtn) return;
                    B020_LINK_COMPONENT_CN();
                    if (pgmRtn) return;
                }
            }
        } else {
            B010_CHECK_INPUT();
            if (pgmRtn) return;
            B020_LINK_COMPONENT();
            if (pgmRtn) return;
        }
    }
    public void B110_BROWSE_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRINVT);
        IBS.init(SCCGWA, BPRVHPB);
        WS_CNT = 0;
        WS_JRNNO = 0;
        BPRINVT.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRINVT.KEY.DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPRINVT.TLR_D = BPC3130.REP_TLR;
        BPRINVT.CB_TYP = '1';
        BPRINVT.INVNTYP = '1';
        BPRINVT.VB_FLG = BPC3130.VB_FLG;
        BPCTINVT.INFO.FUNC = '9';
        BPCTINVT.POINTER = BPRINVT;
        BPCTINVT.LEN = 409;
        S000_CALL_BPZRINVT();
        if (pgmRtn) return;
        BPCTINVT.INFO.FUNC = '2';
        BPCTINVT.POINTER = BPRINVT;
        BPCTINVT.LEN = 409;
        S000_CALL_BPZRINVT();
        if (pgmRtn) return;
        for (WS_CNT = 1; BPCTINVT.RETURN_INFO != 'N'; WS_CNT += 1) {
            BPCTINVT.INFO.FUNC = '2';
            BPCTINVT.POINTER = BPRINVT;
            BPCTINVT.LEN = 409;
            S000_CALL_BPZRINVT();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, WS_CNT);
        }
        CEP.TRC(SCCGWA, WS_CNT);
        BPCTINVT.INFO.FUNC = '3';
        BPCTINVT.LEN = 409;
        BPCTINVT.POINTER = BPRINVT;
        S000_CALL_BPZRINVT();
        if (pgmRtn) return;
        if (BPCTINVT.RETURN_INFO != 'F') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR);
        }
    }
    public void B030_CHECK_ONWAY_FOR_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRBMOV);
        BPRBMOV.MOV_STS = '0';
        BPRBMOV.IN_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        BPRBMOV.IN_TLR = BPC3130.REP_TLR;
        BPRBMOV.ONWAY_DT = 0;
        T000_STARTBR_BPTBMOV();
        if (pgmRtn) return;
        T000_READNEXT_BPTBMOV();
        if (pgmRtn) return;
        for (WS_CNT = 1; WS_TBL_FLAG != 'N'; WS_CNT += 1) {
            if (WS_TBL_FLAG == 'Y') {
                WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_BV_ONWAY_EXIST;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            T000_READNEXT_BPTBMOV();
            if (pgmRtn) return;
        }
        T000_ENDBR_BPTBMOV();
        if (pgmRtn) return;
    }
    public void B040_CHECK_BVLT_CN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRVHPB);
        BPRVHPB.POLL_BOX_IND = BPC3130.VB_FLG;
        BPRVHPB.CUR_TLR = BPC3130.REP_TLR;
        BPRVHPB.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        TOOO_READ_BPTVHPB();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRVHPB.KEY.POOL_BOX_NO);
        IBS.init(SCCGWA, BPRTBVD);
        BPRTBVD.KEY.PL_BOX_NO = BPRVHPB.KEY.POOL_BOX_NO;
        T000_STARTBR_BPTTBVD();
        if (pgmRtn) return;
        T000_READNEXT_BPTTBVD();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPRTBVD.KEY.BV_CODE);
        WS_BV_CODE = BPRTBVD.KEY.BV_CODE;
        for (WS_I = 1; WS_TBL_FLAG != 'N'; WS_I += 1) {
            CEP.TRC(SCCGWA, BPRTBVD.KEY.BV_CODE);
            CEP.TRC(SCCGWA, WS_BV_CODE);
            if (BPRTBVD.KEY.BV_CODE.equalsIgnoreCase(WS_BV_CODE)) {
                WS_BV_NUM = BPRTBVD.NUM + WS_BV_NUM;
                CEP.TRC(SCCGWA, WS_BV_NUM);
            } else {
                IBS.init(SCCGWA, BPRBVLT);
                BPRBVLT.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPRBVLT.KEY.BV_CODE = WS_BV_CODE;
                TOOO_READ_BPTBVLT();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPRBVLT.KEY.BV_CODE);
                CEP.TRC(SCCGWA, WS_BV_NUM);
                CEP.TRC(SCCGWA, BPC3130.VB_FLG);
                if (WS_TBL_FLAG == 'Y') {
                    if (BPC3130.VB_FLG == '0') {
                        CEP.TRC(SCCGWA, WS_BV_NUM);
                        CEP.TRC(SCCGWA, BPRBVLT.LMT_U_BL);
                        CEP.TRC(SCCGWA, BPRBVLT.LMT_L_BL);
                        if (WS_BV_NUM > BPRBVLT.LMT_U_BL) {
                            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_LARGE_WUP_LIMIT_E, BPRBVLT.KEY.BV_CODE);
                            CEP.TRC(SCCGWA, "001");
                        } else if (WS_BV_NUM < BPRBVLT.LMT_L_BL) {
                            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_LESS_WLOW_LIMIT, BPRBVLT.KEY.BV_CODE);
                            CEP.TRC(SCCGWA, "002");
                        } else {
                            CEP.TRC(SCCGWA, "003");
                        }
                        CEP.TRC(SCCGWA, BPRBVLT.KEY.BV_CODE);
                    } else {
                        CEP.TRC(SCCGWA, BPRBVLT.LMT_U_PL);
                        CEP.TRC(SCCGWA, BPRBVLT.LMT_L_PL);
                        if (WS_BV_NUM > BPRBVLT.LMT_U_PL) {
                            CEP.TRC(SCCGWA, "004");
                            SCCMSG SCCMSG = new SCCMSG();
                            IBS.init(SCCGWA, SCCMSG);
                            SCCMSG.MSGID = BPCMSG_ERROR_MSG.BP_LARGE_WUP_LIMIT_E;
                            SCCMSG.INFO = BPRBVLT.KEY.BV_CODE;
                            SCCMSG.TYPE = (char) 'A';
                            SCCMSG.LVL.LVL1 = (char) '5';
                            SCCMSG.LVL.LVL2 = (char) '5';
                            CEP.ERR(SCCGWA, SCCMSG);
                        } else if (WS_BV_NUM < BPRBVLT.LMT_L_PL) {
                            CEP.TRC(SCCGWA, "005");
                            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_LESS_WLOW_LIMIT, BPRBVLT.KEY.BV_CODE);
                        } else {
                            CEP.TRC(SCCGWA, "006");
                        }
                    }
                }
                WS_BV_CODE = " ";
                WS_BV_NUM = 0;
                WS_BV_CODE = BPRTBVD.KEY.BV_CODE;
                WS_BV_NUM = BPRTBVD.NUM + WS_BV_NUM;
            }
            IBS.init(SCCGWA, BPRTBVD);
            T000_READNEXT_BPTTBVD();
            if (pgmRtn) return;
            if (WS_TBL_FLAG == 'N') {
                IBS.init(SCCGWA, BPRBVLT);
                BPRBVLT.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                BPRBVLT.KEY.BV_CODE = WS_BV_CODE;
                TOOO_READ_BPTBVLT();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, BPRBVLT.KEY.BV_CODE);
                CEP.TRC(SCCGWA, WS_BV_NUM);
                CEP.TRC(SCCGWA, BPC3130.VB_FLG);
                if (WS_TBL_FLAG == 'Y') {
                    if (BPC3130.VB_FLG == '0') {
                        CEP.TRC(SCCGWA, WS_BV_NUM);
                        CEP.TRC(SCCGWA, BPRBVLT.LMT_U_BL);
                        CEP.TRC(SCCGWA, BPRBVLT.LMT_L_BL);
                        if (WS_BV_NUM > BPRBVLT.LMT_U_BL) {
                            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_LARGE_WUP_LIMIT_E, BPRBVLT.KEY.BV_CODE);
                            CEP.TRC(SCCGWA, "001");
                        } else if (WS_BV_NUM < BPRBVLT.LMT_L_BL) {
                            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_LESS_WLOW_LIMIT, BPRBVLT.KEY.BV_CODE);
                            CEP.TRC(SCCGWA, "002");
                        } else {
                            CEP.TRC(SCCGWA, "003");
                        }
                        CEP.TRC(SCCGWA, BPRBVLT.KEY.BV_CODE);
                    } else {
                        CEP.TRC(SCCGWA, BPRBVLT.LMT_U_PL);
                        CEP.TRC(SCCGWA, BPRBVLT.LMT_L_PL);
                        if (WS_BV_NUM > BPRBVLT.LMT_U_PL) {
                            CEP.TRC(SCCGWA, "004");
                            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_LARGE_WUP_LIMIT_E, BPRBVLT.KEY.BV_CODE);
                        } else if (WS_BV_NUM < BPRBVLT.LMT_L_PL) {
                            CEP.TRC(SCCGWA, "005");
                            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_LESS_WLOW_LIMIT, BPRBVLT.KEY.BV_CODE);
                        } else {
                            CEP.TRC(SCCGWA, "006");
                        }
                    }
                }
            }
        }
        T000_ENDBR_BPTTBVD();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTTBVD() throws IOException,SQLException,Exception {
        BPTTBVD_BR.rp = new DBParm();
        BPTTBVD_BR.rp.TableName = "BPTTBVD";
        BPTTBVD_BR.rp.where = "PL_BOX_NO = :BPRTBVD.KEY.PL_BOX_NO "
            + "AND STS = '0' "
            + "AND TYPE = '0'";
        BPTTBVD_BR.rp.order = "BV_CODE";
        IBS.STARTBR(SCCGWA, BPRTBVD, this, BPTTBVD_BR);
    }
    public void T000_READNEXT_BPTTBVD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRTBVD, this, BPTTBVD_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
            CEP.TRC(SCCGWA, "WS-TABLE-FND");
            CEP.TRC(SCCGWA, BPRTBVD.KEY.BV_CODE);
            CEP.TRC(SCCGWA, BPRTBVD.NUM);
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
            CEP.TRC(SCCGWA, "WS-TABLE-NOTFND");
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = TBL_BPTTBVD;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.DB_FUNC = "READ";
            B_DB_EXCP();
            if (pgmRtn) return;
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_BPTTBVD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTTBVD_BR);
    }
    public void TOOO_READ_BPTBVLT() throws IOException,SQLException,Exception {
        BPTBVLT_RD = new DBParm();
        BPTBVLT_RD.TableName = "BPTBVLT";
        BPTBVLT_RD.where = "BR = :BPRBVLT.KEY.BR "
            + "AND BV_CODE = :BPRBVLT.KEY.BV_CODE";
        IBS.READ(SCCGWA, BPRBVLT, this, BPTBVLT_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFTLRQ);
        BPCFTLRQ.INFO.TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZFTLRQ();
        if (pgmRtn) return;
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (BPCFTLRQ.INFO.TLR_STSW == null) BPCFTLRQ.INFO.TLR_STSW = "";
        JIBS_tmp_int = BPCFTLRQ.INFO.TLR_STSW.length();
        for (int i=0;i<64-JIBS_tmp_int;i++) BPCFTLRQ.INFO.TLR_STSW += " ";
        if (!BPCFTLRQ.INFO.TLR_STSW.substring(0, 1).equalsIgnoreCase(K_STSW_FLG_Y+"") 
            && !BPCFTLRQ.INFO.TLR_STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase(K_STSW_FLG_Y+"")) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NOT_BV_TLR;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPC3130);
        IBS.init(SCCGWA, BPCFTBVC);
        BPCFTBVC.VB_FLG = BPC3130.VB_FLG;
        BPCFTBVC.BV_FLG = '0';
        for (WS_I = 1; WS_I <= K_MAX_PAR_CNT 
            && BPC3130.BV_INFO[WS_I-1].BV_CODE.trim().length() != 0; WS_I += 1) {
            BPCFTBVC.INFO[WS_I-1].BV_CODE = BPC3130.BV_INFO[WS_I-1].BV_CODE;
            BPCFTBVC.INFO[WS_I-1].BV_STS = BPC3130.BV_INFO[WS_I-1].BV_STS;
            BPCFTBVC.INFO[WS_I-1].NUM = BPC3130.BV_INFO[WS_I-1].NUM;
            BPCFTBVC.IN_CNT = WS_I;
        }
        S00_CALL_BPZFTBVC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BP1398");
        CEP.TRC(SCCGWA, BPCFTBVC.RC);
        JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCFTBVC.RC);
        if (JIBS_tmp_str[0].equalsIgnoreCase(BPCMSG_ERROR_MSG.BP_NUM_TOO_SMALL)) {
            WS_ERR_MSG = BPCMSG_ERROR_MSG.BP_NUM_TOO_SMALL;
