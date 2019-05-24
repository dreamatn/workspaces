package com.hisun.IB;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.AI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class IBZSQRIT {
    String JIBS_tmp_str[] = new String[10];
    DBParm IBTTMAIN_RD;
    DBParm IBTTMST_RD;
    brParm IBTINTH_BR = new brParm();
    brParm IBTINSH_BR = new brParm();
    boolean pgmRtn = false;
    SCCCALL SCCCALL = new SCCCALL();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    char K_INT_INQ = '1';
    char K_INTS_INQ = '2';
    short WS_TS_LEN = 0;
    short WS_TS_CNT = 0;
    short WS_COUNT = 0;
    String WS_TS_REC = "                                                                                                                                                                                                                                                                                                                                                                                              ";
    String WS_TABLE_NAME = " ";
    short WS_I = 0;
    double WS_ROUND_INT = 0;
    IBZSQRIT_WS_RTN_DATA_A WS_RTN_DATA_A = new IBZSQRIT_WS_RTN_DATA_A();
    IBZSQRIT_WS_RTN_DATA_B WS_RTN_DATA_B = new IBZSQRIT_WS_RTN_DATA_B();
    int WS_STR_DATE = 0;
    int WS_END_DATE = 0;
    char WS_TABLE_REC = ' ';
    char WS_TXNBR_FLAG = ' ';
    IBCMSG_ERROR_MSG IBCMSG_ERROR_MSG = new IBCMSG_ERROR_MSG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    IBCPMORG IBCPMORG = new IBCPMORG();
    IBCOSQRT IBCOSQRT = new IBCOSQRT();
    IBCQINF IBCQINF = new IBCQINF();
    AICPQMIB AICPQMIB = new AICPQMIB();
    IBCRODE IBCRODE = new IBCRODE();
    BPCPQORG BPCPQORG = new BPCPQORG();
    IBRTMAIN IBRTMAIN = new IBRTMAIN();
    IBRTMST IBRTMST = new IBRTMST();
    IBRINTH IBRINTH = new IBRINTH();
    IBRINSH IBRINSH = new IBRINSH();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA SCCGSCA_SC_AREA;
    SCCGBPA_BP_AREA SCCGBPA_BP_AREA;
    IBCSQRIT IBCSQRIT;
    public void MP(SCCGWA SCCGWA, IBCSQRIT IBCSQRIT) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.IBCSQRIT = IBCSQRIT;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "IBZSQRIT return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCEXCP);
        SCCGSCA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        SCCGBPA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        IBS.init(SCCGWA, IBCSQRIT.RC);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        if (IBCSQRIT.FUNC == K_INT_INQ) {
            B020_INT_HIS_INQ();
            if (pgmRtn) return;
        } else if (IBCSQRIT.FUNC == K_INTS_INQ) {
            B030_INTS_HIS_INQ();
            if (pgmRtn) return;
        } else {
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + IBCSQRIT.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, IBCSQRIT.PRIM_AC_NO);
        CEP.TRC(SCCGWA, IBCSQRIT.NOSTR_CD);
        CEP.TRC(SCCGWA, IBCSQRIT.CCY);
        if (IBCSQRIT.PRIM_AC_NO.trim().length() == 0 
            && (IBCSQRIT.NOSTR_CD.trim().length() == 0 
            || IBCSQRIT.CCY.trim().length() == 0)) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT_ONE);
        }
        CEP.TRC(SCCGWA, IBCSQRIT.SEQ_NO);
        if (IBCSQRIT.SEQ_NO == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.SEQ_MUST_INPUT);
        }
        WS_STR_DATE = IBCSQRIT.STR_DATE;
        WS_END_DATE = IBCSQRIT.END_DATE;
        CEP.TRC(SCCGWA, WS_STR_DATE);
        CEP.TRC(SCCGWA, WS_END_DATE);
        if (WS_STR_DATE == 0 
            || WS_END_DATE == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.MUST_INPUT);
        } else {
            if (WS_END_DATE < WS_STR_DATE) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.SDATE_GT_EDATE);
            }
        }
        B010_01_CHECK_INPUT();
        if (pgmRtn) return;
        B010_02_CHECK_BR();
        if (pgmRtn) return;
    }
    public void B010_01_CHECK_INPUT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRTMAIN);
        if (IBCSQRIT.PRIM_AC_NO.trim().length() > 0) {
            IBRTMST.PRIM_AC_NO = IBCSQRIT.PRIM_AC_NO;
        } else {
            IBS.init(SCCGWA, IBRTMAIN);
            IBRTMAIN.NOSTRO_CODE = IBCSQRIT.NOSTR_CD;
            IBRTMAIN.CCY = IBCSQRIT.CCY;
            T000_READ_IBTTMAIN();
            if (pgmRtn) return;
            if (WS_TABLE_REC == 'N') {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.PRIM_AC_NOEXIST);
            }
            IBRTMST.PRIM_AC_NO = IBRTMAIN.KEY.AC_NO;
        }
        IBRTMST.SEQ_NO = IBCSQRIT.SEQ_NO;
        T000_READ_IBTTMST();
        if (pgmRtn) return;
        if (WS_TABLE_REC == 'N') {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.AC_NOEXIST);
        }
    }
    public void B010_02_CHECK_BR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = IBRTMST.POST_CTR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        if (!BPCPQORG.VIL_TYP.equalsIgnoreCase("00") 
            || !BPCPQORG.TRA_TYP.equalsIgnoreCase("00")) {
            if (IBRTMST.POST_CTR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH) {
                CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.VIL_TXN_BR);
            }
        } else {
            IBS.init(SCCGWA, BPCPRMR);
            IBS.init(SCCGWA, IBCPMORG);
            IBCPMORG.KEY.TYP = "PIB09";
            IBCPMORG.KEY.CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
            BPCPRMR.DAT_PTR = IBCPMORG;
            S000_CALL_BPZPRMR();
            if (pgmRtn) return;
            CEP.TRC(SCCGWA, IBCPMORG.DATA_TXT.CTL_TYP);
            if (IBCPMORG.DATA_TXT.CTL_TYP == '0') {
                if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH != IBRTMST.POST_CTR) {
                    CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.TXN_BR);
                }
            } else if (IBCPMORG.DATA_TXT.CTL_TYP == '1') {
                for (WS_I = 1; WS_TXNBR_FLAG != 'Y' 
                    && WS_I <= 10; WS_I += 1) {
                    if (SCCGWA.COMM_AREA.BR_DP.TR_BRANCH == IBCPMORG.DATA_TXT.BR[WS_I-1]) {
                        WS_TXNBR_FLAG = 'Y';
                    }
                }
                if (WS_TXNBR_FLAG != 'Y') {
                    CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.TXN_BR);
                }
            } else {
            }
        }
    }
    public void B020_INT_HIS_INQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.MAX_COL_NO = 86;
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.SCR_ROW_CNT = 10;
        B_MPAG();
        if (pgmRtn) return;
        WS_TS_CNT = 0;
        WS_COUNT = 0;
        B020_01_INTS_INQ_PROC();
        if (pgmRtn) return;
    }
    public void B020_01_INTS_INQ_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRINTH);
        IBRINTH.KEY.AC_NO = IBRTMST.KEY.AC_NO;
        T000_STARTBR_IBTINTH();
        if (pgmRtn) return;
        while (WS_TABLE_REC != 'N' 
            && SCCMPAG.FUNC != 'E') {
            T000_READNEXT_IBTINTH();
            if (pgmRtn) return;
            if (WS_TABLE_REC == 'Y') {
                WS_ROUND_INT = IBRINTH.INT;
                B020_01_01_GET_ROUND();
                if (pgmRtn) return;
                WS_RTN_DATA_A.WS_1_INT = IBCRODE.INT_AMT;
                WS_ROUND_INT = IBRINTH.BUD_INT;
                B020_01_01_GET_ROUND();
                if (pgmRtn) return;
                WS_RTN_DATA_A.WS_1_BUD_INT = IBCRODE.INT_AMT;
                WS_RTN_DATA_A.WS_1_INT_DATE = IBRINTH.KEY.INT_DATE;
                WS_RTN_DATA_A.WS_1_END_BAL = IBRINTH.END_BAL;
                WS_RTN_DATA_A.WS_1_INT_RATE = IBRINTH.INT_RATE;
                WS_TS_REC = IBS.CLS2CPY(SCCGWA, WS_RTN_DATA_A);
                WS_TS_LEN = 72;
                R000_WRITE_QUEUE();
                if (pgmRtn) return;
                WS_COUNT = (short) (WS_COUNT + 1);
            }
        }
        CEP.TRC(SCCGWA, WS_COUNT);
        if (WS_COUNT == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NO_REC);
        }
        T000_ENDBR_IBTINTH();
        if (pgmRtn) return;
    }
    public void B020_01_01_GET_ROUND() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBCRODE);
        IBCRODE.INT_AMT = WS_ROUND_INT;
        IBCRODE.CCY = IBRTMST.CCY;
        S000_CALL_IBZRODE();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, IBCRODE.INT_AMT);
    }
    public void B030_INTS_HIS_INQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.MAX_COL_NO = 86;
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.SCR_ROW_CNT = 100;
        B_MPAG();
        if (pgmRtn) return;
        WS_TS_CNT = 0;
        WS_COUNT = 0;
        B030_01_INTS_INQ_PROC();
        if (pgmRtn) return;
    }
    public void B030_01_INTS_INQ_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, IBRINSH);
        IBRINSH.KEY.AC_NO = IBRTMST.KEY.AC_NO;
        T000_STARTBR_IBTINSH();
        if (pgmRtn) return;
        while (WS_TABLE_REC != 'N' 
            && SCCMPAG.FUNC != 'E') {
            T000_READNEXT_IBTINSH();
            if (pgmRtn) return;
            if (WS_TABLE_REC == 'Y') {
                WS_RTN_DATA_B.WS_2_DEAL_DATE = IBRINSH.KEY.DEAL_DATE;
                WS_RTN_DATA_B.WS_2_JRN_NO = IBRINSH.JRN_NO;
                WS_RTN_DATA_B.WS_2_SEQ = IBRINSH.KEY.SEQ;
                WS_RTN_DATA_B.WS_2_INTS_DATE = IBRINSH.INTS_DATE;
                WS_RTN_DATA_B.WS_2_CCY = IBRTMST.CCY;
                WS_ROUND_INT = IBRINSH.ESET_AMT;
                B020_01_01_GET_ROUND();
                if (pgmRtn) return;
                WS_RTN_DATA_B.WS_2_ESET_AMT = IBCRODE.INT_AMT;
                WS_RTN_DATA_B.WS_2_ASET_AMT = IBRINSH.ASET_AMT;
                CEP.TRC(SCCGWA, WS_RTN_DATA_B.WS_2_ASET_AMT);
                WS_RTN_DATA_B.WS_2_SETT_AC_NO = IBRINSH.SETT_AC_NO;
                WS_RTN_DATA_B.WS_2_SETT_TYPE = IBRINSH.SETT_TYPE;
                B030_02_GET_AC_NAME();
                if (pgmRtn) return;
                WS_RTN_DATA_B.WS_2_REV_FLG = IBRINSH.REV_FLG;
                WS_RTN_DATA_B.WS_2_CRT_DATE = IBRINSH.CRT_DATE;
                WS_RTN_DATA_B.WS_2_OP_TLR = IBRINSH.CRT_TLR;
                WS_RTN_DATA_B.WS_2_AUTH_TLR = IBRINSH.AUTH_TLR;
                WS_TS_REC = IBS.CLS2CPY(SCCGWA, WS_RTN_DATA_B);
                WS_TS_LEN = 382;
                R000_WRITE_QUEUE();
                if (pgmRtn) return;
                WS_COUNT = (short) (WS_COUNT + 1);
            }
        }
        CEP.TRC(SCCGWA, WS_COUNT);
        if (WS_COUNT == 0) {
            CEP.ERR(SCCGWA, IBCMSG_ERROR_MSG.NO_REC);
        }
        T000_ENDBR_IBTINSH();
        if (pgmRtn) return;
    }
    public void B030_02_GET_AC_NAME() throws IOException,SQLException,Exception {
        if (IBRINSH.SETT_AC_TYPE == 'I') {
            IBS.init(SCCGWA, AICPQMIB);
            AICPQMIB.INPUT_DATA.AC = IBRINSH.SETT_AC_NO;
            S000_CALL_AIZPQMIB();
            if (pgmRtn) return;
            WS_RTN_DATA_B.WS_2_SETT_AC_NAME = AICPQMIB.OUTPUT_DATA.CHS_NM;
        }
        if (IBRINSH.SETT_AC_TYPE == 'N') {
            IBS.init(SCCGWA, IBCQINF);
            IBCQINF.INPUT_DATA.AC_NO = IBRINSH.SETT_AC_NO;
            S000_CALL_IBZQINF();
            if (pgmRtn) return;
            WS_RTN_DATA_B.WS_2_SETT_AC_NAME = IBCQINF.OUTPUT_DATA.AC_CHN_NAME;
        }
    }
    public void R000_WRITE_QUEUE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.MAX_COL_NO = 382;
        SCCMPAG.DATA_LEN = WS_TS_LEN;
        SCCMPAG.DATA_PTR = WS_TS_REC;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPRMR() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-PARM-READ", BPCPRMR);
        if (BPCPRMR.RC.RC_RTNCODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPRMR.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_IBZQINF() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-IBZQINF", IBCQINF);
        if (IBCQINF.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCQINF.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_AIZPQMIB() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "AI-P-INQ-MIB", AICPQMIB);
        if (AICPQMIB.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, AICPQMIB.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_IBZRODE() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "IB-ROUND-INT", IBCRODE);
        if (IBCRODE.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, IBCRODE.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void T000_READ_IBTTMAIN() throws IOException,SQLException,Exception {
        IBTTMAIN_RD = new DBParm();
        IBTTMAIN_RD.TableName = "IBTTMAIN";
        IBTTMAIN_RD.where = "NOSTRO_CODE = :IBRTMAIN.NOSTRO_CODE "
            + "AND CCY = :IBRTMAIN.CCY";
        IBS.READ(SCCGWA, IBRTMAIN, this, IBTTMAIN_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = WS_TABLE_NAME;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_READ_IBTTMST() throws IOException,SQLException,Exception {
        IBTTMST_RD = new DBParm();
        IBTTMST_RD.TableName = "IBTTMST";
        IBTTMST_RD.where = "PRIM_AC_NO = :IBRTMST.PRIM_AC_NO "
            + "AND SEQ_NO = :IBRTMST.SEQ_NO";
        IBS.READ(SCCGWA, IBRTMST, this, IBTTMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = WS_TABLE_NAME;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_IBTINTH() throws IOException,SQLException,Exception {
        IBTINTH_BR.rp = new DBParm();
        IBTINTH_BR.rp.TableName = "IBTINTH";
        IBTINTH_BR.rp.where = "INT_DATE >= :WS_STR_DATE "
            + "AND INT_DATE <= :WS_END_DATE "
            + "AND AC_NO = :IBRINTH.KEY.AC_NO";
        IBTINTH_BR.rp.order = "INT_DATE";
        IBS.STARTBR(SCCGWA, IBRINTH, this, IBTINTH_BR);
    }
    public void T000_READNEXT_IBTINTH() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, IBRINTH, this, IBTINTH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = WS_TABLE_NAME;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_STARTBR_IBTINSH() throws IOException,SQLException,Exception {
        IBTINSH_BR.rp = new DBParm();
        IBTINSH_BR.rp.TableName = "IBTINSH";
        IBTINSH_BR.rp.where = "DEAL_DATE >= :WS_STR_DATE "
            + "AND DEAL_DATE <= :WS_END_DATE "
            + "AND AC_NO = :IBRINSH.KEY.AC_NO";
        IBTINSH_BR.rp.order = "DEAL_DATE";
        IBS.STARTBR(SCCGWA, IBRINSH, this, IBTINSH_BR);
    }
    public void T000_READNEXT_IBTINSH() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, IBRINSH, this, IBTINSH_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TABLE_REC = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TABLE_REC = 'N';
        } else {
            SCCEXCP.MSG_TEXT.MSG_TEXT_D.TABLE_NAME = WS_TABLE_NAME;
            SCCEXCP.MSG_TEXT.SYS_INFO = IBS.CLS2CPY(SCCGWA, SCCEXCP.MSG_TEXT.MSG_TEXT_D);
            B_DB_EXCP();
            if (pgmRtn) return;
        }
    }
    public void T000_ENDBR_IBTINTH() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, IBTINTH_BR);
    }
    public void T000_ENDBR_IBTINSH() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, IBTINSH_BR);
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        pgmRtn = true;
        return;
    }
    public void B_MPAG() throws IOException,SQLException,Exception {
    if (!SCCGWA.COMM_AREA.BSP_FLG.equalsIgnoreCase("BSP") && !SCCGWA.COMM_AREA.CHNL.equalsIgnoreCase("BAT")) { //FROM #IFDEF ONL
        JIBS_tmp_str[9] = "SCZMPAG";
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_M);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_C);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_D);
        IBS.CPY2CLS(SCCGWA, JIBS_tmp_str[9], SCCEXCP.MSG_TEXT.MSG_TEXT_Q);
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
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
