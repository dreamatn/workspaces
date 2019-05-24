package com.hisun.EQ;

import com.hisun.SC.*;
import com.hisun.BP.*;
import com.hisun.CI.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class EQZSPLG {
    int JIBS_tmp_int;
    DBParm EQTACT_RD;
    brParm EQTPLG_BR = new brParm();
    DBParm EQTBVT_RD;
    brParm EQTPLGD_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    DBParm EQTPLG_RD;
    DBParm EQTPLGD_RD;
    boolean pgmRtn = false;
    int K_MAX_ROW = 10;
    int K_MAX_COL = 500;
    String K_OUTPUT_FMT_9 = "EQ303";
    String K_CMP_CALL_BPZPQPRD = "BP-P-QUERY-PRDT-INFO";
    String K_BSZ_BANKID = "01";
    char K_EQACT_STS_CLOSED = 'C';
    char K_EQPLG_STS_NORMAL = 'N';
    char K_EQPLG_STS_CLOSED = 'C';
    char K_EQPLG_CONF_TYPE = '1';
    char K_EQ_RVS_FLG = 'N';
    String WS_ERR_MSG = " ";
    short WS_FLD_NO = 0;
    char WS_CIT_FLG = ' ';
    char WS_ACT_FLG = ' ';
    char WS_BVT_FLG = ' ';
    char WS_PLG_FLG = ' ';
    char WS_PLGD_FLG = ' ';
    String WS_TX_MMO = " ";
    char WS_NORMAL_STS = 'N';
    EQCMSG_ERROR_MSG EQCMSG_ERROR_MSG = new EQCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    EQRACT EQRACT = new EQRACT();
    EQRACT EQRACTO = new EQRACT();
    EQRACT EQRACTN = new EQRACT();
    EQRBVT EQRBVT = new EQRBVT();
    EQRPLG EQRPLG = new EQRPLG();
    EQRPLGD EQRPLGD = new EQRPLGD();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    EQCRACT EQCRACT = new EQCRACT();
    CICCUST CICCUST = new CICCUST();
    EQCO8300_OPT_8300 EQCO8300_OPT_8300 = new EQCO8300_OPT_8300();
    EQCO8303_OPT_8303 EQCO8303_OPT_8303 = new EQCO8303_OPT_8303();
    EQCO8304_OPT_8304 EQCO8304_OPT_8304 = new EQCO8304_OPT_8304();
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    EQCMPPLG EQCMPPLG;
    public void MP(SCCGWA SCCGWA, EQCMPPLG EQCMPPLG) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.EQCMPPLG = EQCMPPLG;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "EQZSPLG return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT();
        if (pgmRtn) return;
        B020_MAIN_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EQCMPPLG.DATA.EQ_BKID);
        CEP.TRC(SCCGWA, EQCMPPLG.DATA.CI_NO);
        CEP.TRC(SCCGWA, EQCMPPLG.DATA.EQ_ACT);
        CEP.TRC(SCCGWA, EQCMPPLG.DATA.EQ_CINO);
        CEP.TRC(SCCGWA, EQCMPPLG.DATA.EQ_AC);
        CEP.TRC(SCCGWA, EQCMPPLG.DATA.PLG_NO);
        if (EQCMPPLG.DATA.EQ_BKID.trim().length() == 0) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_BANKID_MUST_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B020_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EQCMPPLG.FUNC);
        if (EQCMPPLG.FUNC == 'I') {
            B021_INQUIRE_PROC();
            if (pgmRtn) return;
        } else if (EQCMPPLG.FUNC == 'A') {
            B022_ADD_PROC();
            if (pgmRtn) return;
        } else if (EQCMPPLG.FUNC == 'M') {
            B023_MODIFY_PROC();
            if (pgmRtn) return;
        } else if (EQCMPPLG.FUNC == 'B') {
            B025_BROWSE_ALL_PROC();
            if (pgmRtn) return;
        } else if (EQCMPPLG.FUNC == 'O') {
            B026_BROWSE_PROC();
            if (pgmRtn) return;
        } else {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_INVALID_FUNC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void B021_INQUIRE_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EQCMPPLG.DATA.CI_NO);
        if (EQCMPPLG.DATA.CI_NO.trim().length() > 0) {
            T000_READ_CITBAS();
            if (pgmRtn) return;
            if (WS_CIT_FLG != 'Y') {
                CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_NOTFND);
            }
        }
        EQRACT.CI_NO = EQCMPPLG.DATA.CI_NO;
        EQRACT.EQ_ACT = EQCMPPLG.DATA.EQ_ACT;
        EQRACT.EQ_CINO = EQCMPPLG.DATA.EQ_CINO;
        EQRACT.KEY.EQ_AC = EQCMPPLG.DATA.EQ_AC;
        if (EQCMPPLG.DATA.CI_NO.trim().length() > 0 
            || EQCMPPLG.DATA.EQ_ACT.trim().length() > 0 
            || EQCMPPLG.DATA.EQ_CINO.trim().length() > 0 
            || EQCMPPLG.DATA.EQ_AC.trim().length() > 0) {
            T000_READ_EQACT();
            if (pgmRtn) return;
            if (WS_ACT_FLG == 'N') {
                CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_NOTFND);
            }
        }
        T000_READ_EQTPLG_ALL();
        if (pgmRtn) return;
        T000_READNEXT_EQTPLG();
        if (pgmRtn) return;
        if (WS_PLG_FLG == 'Y') {
            B020_OUT_TITLE();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_PLG_NOTFND);
        }
        while (WS_PLG_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B021_OUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_EQTPLG();
            if (pgmRtn) return;
        }
        T000_ENDBR_EQTPLG();
        if (pgmRtn) return;
    }
    public void B022_ADD_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, EQCMPPLG.DATA.CI_NO);
        if (EQCMPPLG.DATA.CI_NO.trim().length() > 0) {
            T000_READ_CITBAS();
            if (pgmRtn) return;
            if (WS_CIT_FLG != 'Y') {
                CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_NOTFND);
            }
        }
        IBS.init(SCCGWA, EQRACT);
        IBS.init(SCCGWA, EQRACTO);
        IBS.init(SCCGWA, EQRACTN);
        EQRACT.CI_NO = EQCMPPLG.DATA.CI_NO;
        EQRACT.EQ_ACT = EQCMPPLG.DATA.EQ_ACT;
        EQRACT.EQ_CINO = EQCMPPLG.DATA.EQ_CINO;
        EQRACT.KEY.EQ_AC = EQCMPPLG.DATA.EQ_AC;
        CEP.TRC(SCCGWA, EQCMPPLG.DATA.PLG_QTY);
        if (EQCMPPLG.DATA.CI_NO.trim().length() > 0 
            || EQCMPPLG.DATA.EQ_ACT.trim().length() > 0 
            || EQCMPPLG.DATA.EQ_CINO.trim().length() > 0 
            || EQCMPPLG.DATA.EQ_AC.trim().length() > 0) {
            T000_READ_EQACT();
            if (pgmRtn) return;
            if (WS_ACT_FLG == 'N') {
                CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_NOTFND);
            } else {
                CEP.TRC(SCCGWA, EQRACT.AC_STS);
                CEP.TRC(SCCGWA, EQRACT.EQ_QTY);
                CEP.TRC(SCCGWA, EQRACT.FRZ_QTY);
                CEP.TRC(SCCGWA, EQRACT.PLG_QTY);
                if (EQRACT.AC_STS == K_EQACT_STS_CLOSED) {
                    CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_AC_CLOSED);
                }
                if (( EQRACT.EQ_QTY - EQRACT.FRZ_QTY - EQRACT.PLG_QTY ) < EQCMPPLG.DATA.PLG_QTY) {
                    CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_QTY_NOT_ENOUGH);
                }
            }
        } else {
            CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_EQTACT_MUST_INPUT);
        }
        T000_GET_PLGNO();
        if (pgmRtn) return;
        R000_WRITE_EQTPLG();
        if (pgmRtn) return;
        R000_WRITE_EQTPLGD();
        if (pgmRtn) return;
        EQCRACT.FUNC = 'R';
        S000_CALL_EQZRACT();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, EQRACT, EQRACTO);
        EQRACT.PLG_QTY = EQRACT.PLG_QTY + EQCMPPLG.DATA.PLG_QTY;
        CEP.TRC(SCCGWA, EQRACT.EQ_QTY);
        CEP.TRC(SCCGWA, EQRACT.FRZ_QTY);
        CEP.TRC(SCCGWA, EQRACT.PLG_QTY);
        EQRACT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRACT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRACT.TS = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = EQRACT.TS.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) EQRACT.TS = "0" + EQRACT.TS;
        EQCRACT.FUNC = 'U';
        S000_CALL_EQZRACT();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, EQRACT, EQRACTN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        WS_TX_MMO = "P150";
        R000_WRT_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B023_MODIFY_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRACT);
        IBS.init(SCCGWA, EQRACTO);
        IBS.init(SCCGWA, EQRACTN);
        CEP.TRC(SCCGWA, EQCMPPLG.DATA.CI_NO);
        if (EQCMPPLG.DATA.CI_NO.trim().length() > 0) {
            T000_READ_CITBAS();
            if (pgmRtn) return;
            if (WS_CIT_FLG != 'Y') {
                CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_NOTFND);
            }
        }
        T000_READ_EQTPLG_ALL();
        if (pgmRtn) return;
        T000_READNEXT_EQTPLG();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, EQRPLG.PLG_QTY);
        CEP.TRC(SCCGWA, EQRPLG.KEY.EQ_AC);
        if (WS_PLG_FLG == 'Y') {
            if (EQRPLG.PLG_STS == K_EQPLG_STS_CLOSED) {
                CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_PLG_STS_IS_REL);
            }
            if (EQRPLG.PLG_QTY < EQCMPPLG.DATA.REL_QTY) {
                CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_REL_QTY_G_PLG);
            }
        } else {
            CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_PLG_NOTFND);
        }
        EQCMPPLG.DATA.EQ_AC = EQRPLG.KEY.EQ_AC;
        CEP.TRC(SCCGWA, EQCMPPLG.DATA.EQ_AC);
        T000_READ_EQACT();
        if (pgmRtn) return;
        if (WS_ACT_FLG == 'N') {
            CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_NOTFND);
        } else {
            CEP.TRC(SCCGWA, EQRACT.AC_STS);
            CEP.TRC(SCCGWA, EQRACT.EQ_QTY);
            CEP.TRC(SCCGWA, EQRACT.FRZ_QTY);
            CEP.TRC(SCCGWA, EQRACT.PLG_QTY);
            if (EQRACT.AC_STS == K_EQACT_STS_CLOSED) {
                CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_AC_CLOSED);
            }
        }
        CEP.TRC(SCCGWA, EQRACT.KEY.EQ_AC);
        T000_READ_UPDATE_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, EQCMPPLG.DATA.REL_QTY);
        CEP.TRC(SCCGWA, EQRPLG.PLG_QTY);
        CEP.TRC(SCCGWA, EQRPLG.REL_QTY);
        EQRPLG.PLG_QTY = EQRPLG.PLG_QTY - EQCMPPLG.DATA.REL_QTY;
        EQRPLG.REL_QTY = EQRPLG.REL_QTY + EQCMPPLG.DATA.REL_QTY;
        if (EQRPLG.PLG_QTY == 0) {
            EQRPLG.PLG_STS = K_EQPLG_STS_CLOSED;
        }
        EQRPLG.REL_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        EQRPLG.REL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRPLG.REL_DT = SCCGWA.COMM_AREA.AC_DATE;
        EQRPLG.REL_NUM = EQCMPPLG.DATA.REL_NUM;
        EQRPLG.REMARK = EQCMPPLG.DATA.REMARK;
        EQRPLG.PART_REL_DT = SCCGWA.COMM_AREA.AC_DATE;
        EQRPLG.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRPLG.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRPLG.TS = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = EQRPLG.TS.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) EQRPLG.TS = "0" + EQRPLG.TS;
        T000_REWRITE_REC_PROC();
        if (pgmRtn) return;
        R000_WRITE_EQTPLGD();
        if (pgmRtn) return;
        EQCRACT.FUNC = 'R';
        S000_CALL_EQZRACT();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, EQRACT, EQRACTO);
        EQRACT.PLG_QTY = EQRACT.PLG_QTY - EQCMPPLG.DATA.REL_QTY;
        CEP.TRC(SCCGWA, EQRACT.EQ_QTY);
        CEP.TRC(SCCGWA, EQRACT.FRZ_QTY);
        CEP.TRC(SCCGWA, EQRACT.PLG_QTY);
        EQRACT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRACT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRACT.TS = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = EQRACT.TS.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) EQRACT.TS = "0" + EQRACT.TS;
        EQCRACT.FUNC = 'U';
        S000_CALL_EQZRACT();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, EQRACT, EQRACTN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        WS_TX_MMO = "P151";
        R000_WRT_HIS_PROC();
        if (pgmRtn) return;
    }
    public void T000_READ_CITBAS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = EQCMPPLG.DATA.CI_NO;
        CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
        CICCUST.FUNC = 'C';
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICCUST.RC.RC_CODE);
        if (CICCUST.RC.RC_CODE == 0) {
            if (EQCMPPLG.DATA.CI_CNM.trim().length() > 0) {
                if (!EQCMPPLG.DATA.CI_CNM.equalsIgnoreCase(CICCUST.O_DATA.O_CI_NM)) {
                    CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_PLG_NOTFND);
                }
            }
            if (EQCMPPLG.DATA.ID_TYP.trim().length() > 0) {
                if (!EQCMPPLG.DATA.ID_TYP.equalsIgnoreCase(CICCUST.O_DATA.O_ID_TYPE)) {
                    CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_PLG_NOTFND);
                }
            }
            if (EQCMPPLG.DATA.ID_NO.trim().length() > 0) {
                if (!EQCMPPLG.DATA.ID_NO.equalsIgnoreCase(CICCUST.O_DATA.O_ID_NO)) {
                    CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_PLG_NOTFND);
                }
            }
        } else {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_CINO_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        WS_CIT_FLG = 'Y';
    }
    public void T000_READ_EQACT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRACT);
        EQRACT.KEY.EQ_BKID = EQCMPPLG.DATA.EQ_BKID;
        EQRACT.CI_NO = EQCMPPLG.DATA.CI_NO;
        EQRACT.EQ_ACT = EQCMPPLG.DATA.EQ_ACT;
        EQRACT.EQ_CINO = EQCMPPLG.DATA.EQ_CINO;
        EQRACT.KEY.EQ_AC = EQCMPPLG.DATA.EQ_AC;
        CEP.TRC(SCCGWA, EQCMPPLG.DATA.EQ_BKID);
        CEP.TRC(SCCGWA, EQCMPPLG.DATA.CI_NO);
        CEP.TRC(SCCGWA, EQCMPPLG.DATA.EQ_ACT);
        CEP.TRC(SCCGWA, EQCMPPLG.DATA.EQ_CINO);
        CEP.TRC(SCCGWA, EQCMPPLG.DATA.EQ_AC);
        CEP.TRC(SCCGWA, EQRACT.KEY.EQ_BKID);
        CEP.TRC(SCCGWA, EQRACT.CI_NO);
        CEP.TRC(SCCGWA, EQRACT.EQ_ACT);
        CEP.TRC(SCCGWA, EQRACT.EQ_CINO);
        CEP.TRC(SCCGWA, EQRACT.KEY.EQ_AC);
        EQTACT_RD = new DBParm();
        EQTACT_RD.TableName = "EQTACT";
        EQTACT_RD.where = "EQ_BKID = :EQRACT.KEY.EQ_BKID "
            + "AND ( ' ' = :EQRACT.CI_NO "
            + "OR CI_NO = :EQRACT.CI_NO ) "
            + "AND ( ' ' = :EQRACT.KEY.EQ_AC "
            + "OR EQ_AC = :EQRACT.KEY.EQ_AC ) "
            + "AND ( ' ' = :EQRACT.EQ_ACT "
            + "OR EQ_ACT = :EQRACT.EQ_ACT ) "
            + "AND ( ' ' = :EQRACT.EQ_CINO "
            + "OR EQ_CINO = :EQRACT.EQ_CINO )";
        EQTACT_RD.fst = true;
        IBS.READ(SCCGWA, EQRACT, this, EQTACT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ACT_FLG = 'N';
        } else {
        }
        EQCMPPLG.DATA.EQ_AC = EQRACT.KEY.EQ_AC;
    }
    public void T000_READ_EQTPLG_ALL() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRPLG);
        EQRPLG.KEY.EQ_BKID = EQCMPPLG.DATA.EQ_BKID;
        EQRPLG.KEY.EQ_AC = EQCMPPLG.DATA.EQ_AC;
        EQRPLG.PLG_NO = EQCMPPLG.DATA.PLG_NO;
        EQRPLG.PLG_STS = EQCMPPLG.DATA.PLG_STS;
        CEP.TRC(SCCGWA, EQCMPPLG.DATA.EQ_BKID);
        CEP.TRC(SCCGWA, EQCMPPLG.DATA.EQ_AC);
        CEP.TRC(SCCGWA, EQCMPPLG.DATA.PLG_NO);
        CEP.TRC(SCCGWA, EQCMPPLG.DATA.PLG_STS);
        CEP.TRC(SCCGWA, EQRPLG.KEY.EQ_BKID);
        CEP.TRC(SCCGWA, EQRPLG.KEY.EQ_AC);
        CEP.TRC(SCCGWA, EQRPLG.PLG_NO);
        CEP.TRC(SCCGWA, EQRPLG.PLG_STS);
        EQTPLG_BR.rp = new DBParm();
        EQTPLG_BR.rp.TableName = "EQTPLG";
        EQTPLG_BR.rp.where = "EQ_BKID = :EQRPLG.KEY.EQ_BKID "
            + "AND ( ' ' = :EQRPLG.KEY.EQ_AC "
            + "OR EQ_AC = :EQRPLG.KEY.EQ_AC ) "
            + "AND ( ' ' = :EQRPLG.PLG_NO "
            + "OR PLG_NO = :EQRPLG.PLG_NO ) "
            + "AND ( ' ' = :EQRPLG.PLG_STS "
            + "OR PLG_STS = :EQRPLG.PLG_STS )";
        IBS.STARTBR(SCCGWA, EQRPLG, this, EQTPLG_BR);
    }
    public void B025_BROWSE_ALL_PROC() throws IOException,SQLException,Exception {
        T000_READ_EQTPLG_ALL();
        if (pgmRtn) return;
        T000_READNEXT_EQTPLG();
        if (pgmRtn) return;
        if (WS_PLG_FLG == 'Y') {
            B020_OUT_TITLE();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_PLG_NOTFND);
        }
        B025_OUT_BRW_DATA();
        if (pgmRtn) return;
        T000_ENDBR_EQTPLG();
        if (pgmRtn) return;
    }
    public void B026_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRPLGD);
        EQRPLGD.KEY.EQ_BKID = EQCMPPLG.DATA.EQ_BKID;
        EQRPLGD.KEY.EQ_AC = EQCMPPLG.DATA.EQ_AC;
        EQRPLGD.KEY.PLG_NO = EQCMPPLG.DATA.PLG_NO;
        T003_STARTBR_EQTPLGD();
        if (pgmRtn) return;
        T000_READNEXT_EQTPLGD();
        if (pgmRtn) return;
        if (WS_PLGD_FLG == 'Y') {
            B020_OUT_TITLE();
            if (pgmRtn) return;
        } else {
            CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_PLG_NOTFND);
        }
        while (WS_PLGD_FLG != 'N' 
            && SCCMPAG.FUNC != 'E') {
            B026_OUT_BRW_DATA();
            if (pgmRtn) return;
            T000_READNEXT_EQTPLGD();
            if (pgmRtn) return;
        }
        T000_ENDBR_EQTPLGD();
        if (pgmRtn) return;
    }
    public void B020_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.MAX_COL_NO = (short) K_MAX_COL;
        SCCMPAG.SCR_ROW_CNT = (short) K_MAX_ROW;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B021_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQCO8300_OPT_8300);
        IBS.init(SCCGWA, EQRACT);
        EQRACT.KEY.EQ_BKID = EQRPLG.KEY.EQ_BKID;
        EQRACT.KEY.EQ_AC = EQRPLG.KEY.EQ_AC;
        CEP.TRC(SCCGWA, EQRACT.KEY.EQ_AC);
        T003_READ_EQTACT_BY_EQAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, EQRACT.CI_NO);
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = EQRACT.CI_NO;
        CICCUST.FUNC = 'C';
        CEP.TRC(SCCGWA, CICCUST.DATA.CI_NO);
        CEP.TRC(SCCGWA, EQRACT.CI_NO);
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        if (CICCUST.RC.RC_CODE == 0) {
            EQCO8300_OPT_8300.EQ_CNM = CICCUST.O_DATA.O_CI_NM;
            EQCO8300_OPT_8300.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
            EQCO8300_OPT_8300.ID_NO = CICCUST.O_DATA.O_ID_NO;
        } else {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_CINO_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        EQCO8300_OPT_8300.EQ_BKID = EQRACT.KEY.EQ_BKID;
        EQCO8300_OPT_8300.EQ_AC = EQRACT.KEY.EQ_AC;
        EQCO8300_OPT_8300.EQ_ACT = EQRACT.EQ_ACT;
        EQCO8300_OPT_8300.EQ_CINO = EQRACT.EQ_CINO;
        EQCO8300_OPT_8300.PLG_NUM = EQRPLG.PLG_NUM;
        EQCO8300_OPT_8300.PLG_NO = EQRPLG.PLG_NO;
        EQCO8300_OPT_8300.PLG_QTY = EQRPLG.PLG_QTY;
        EQCO8300_OPT_8300.REL_QTY = EQRPLG.REL_QTY;
        EQCO8300_OPT_8300.EFF_DT = EQRPLG.EFF_DT;
        EQCO8300_OPT_8300.REL_DT = EQRPLG.REL_DT;
        EQCO8300_OPT_8300.PLG_STS = EQRPLG.PLG_STS;
        EQCO8300_OPT_8300.REMARK = EQRPLG.REMARK;
        CEP.TRC(SCCGWA, EQCO8300_OPT_8300.EQ_CNM);
        CEP.TRC(SCCGWA, EQCO8300_OPT_8300.ID_TYP);
        CEP.TRC(SCCGWA, EQCO8300_OPT_8300.ID_NO);
        CEP.TRC(SCCGWA, EQCO8300_OPT_8300.EQ_BKID);
        CEP.TRC(SCCGWA, EQCO8300_OPT_8300.EQ_AC);
        CEP.TRC(SCCGWA, EQCO8300_OPT_8300.EQ_ACT);
        CEP.TRC(SCCGWA, EQCO8300_OPT_8300.EQ_CINO);
        CEP.TRC(SCCGWA, EQCO8300_OPT_8300.PLG_NUM);
        CEP.TRC(SCCGWA, EQCO8300_OPT_8300.PLG_NO);
        CEP.TRC(SCCGWA, EQCO8300_OPT_8300.PLG_QTY);
        CEP.TRC(SCCGWA, EQCO8300_OPT_8300.REL_QTY);
        CEP.TRC(SCCGWA, EQCO8300_OPT_8300.EFF_DT);
        CEP.TRC(SCCGWA, EQCO8300_OPT_8300.REL_DT);
        CEP.TRC(SCCGWA, EQCO8300_OPT_8300.PLG_STS);
        CEP.TRC(SCCGWA, EQCO8300_OPT_8300.REMARK);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, EQCO8300_OPT_8300);
        SCCMPAG.DATA_LEN = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void B025_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQCO8303_OPT_8303);
        IBS.init(SCCGWA, EQRACT);
        EQRACT.KEY.EQ_BKID = EQRPLG.KEY.EQ_BKID;
        EQRACT.KEY.EQ_AC = EQRPLG.KEY.EQ_AC;
        T003_READ_EQTACT_BY_EQAC();
        if (pgmRtn) return;
        T000_READ_EQTBVT_FIRST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = EQRACT.CI_NO;
        CICCUST.FUNC = 'C';
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        if (CICCUST.RC.RC_CODE == 0) {
            EQCO8303_OPT_8303.EQ_CNM = CICCUST.O_DATA.O_CI_NM;
            EQCO8303_OPT_8303.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
            EQCO8303_OPT_8303.ID_NO = CICCUST.O_DATA.O_ID_NO;
        } else {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_CINO_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        EQCO8303_OPT_8303.EQ_BKID = EQRACT.KEY.EQ_BKID;
        EQCO8303_OPT_8303.EQ_AC = EQRACT.KEY.EQ_AC;
        EQCO8303_OPT_8303.EQ_ACT = EQRACT.EQ_ACT;
        EQCO8303_OPT_8303.EQ_CINO = EQRACT.EQ_CINO;
        EQCO8303_OPT_8303.PSBK_NO = EQRBVT.KEY.PSBK_NO;
        EQCO8303_OPT_8303.PLG_NUM = EQRPLG.PLG_NUM;
        EQCO8303_OPT_8303.PLG_NO = EQRPLG.PLG_NO;
        EQCO8303_OPT_8303.EQ_QTY = EQRACT.EQ_QTY;
        EQCO8303_OPT_8303.CI_NO = EQRACT.CI_NO;
        EQCO8303_OPT_8303.PLG_QTY = EQRPLG.PLG_QTY;
        EQCO8303_OPT_8303.REL_QTY = EQRPLG.REL_QTY;
        EQCO8303_OPT_8303.GRT_AMT = EQRPLG.GRT_AMT;
        EQCO8303_OPT_8303.ASMT_AMT = EQRPLG.ASMT_AMT;
        EQCO8303_OPT_8303.EFF_DT = EQRPLG.EFF_DT;
        EQCO8303_OPT_8303.REL_DT = EQRPLG.REL_DT;
        EQCO8303_OPT_8303.REL_NUM = EQRPLG.REL_NUM;
        EQCO8303_OPT_8303.PLG_STS = EQRPLG.PLG_STS;
        EQCO8303_OPT_8303.REMARK = EQRPLG.REMARK;
        CEP.TRC(SCCGWA, EQCO8303_OPT_8303.EQ_CNM);
        CEP.TRC(SCCGWA, EQCO8303_OPT_8303.ID_TYP);
        CEP.TRC(SCCGWA, EQCO8303_OPT_8303.ID_NO);
        CEP.TRC(SCCGWA, EQCO8303_OPT_8303.EQ_BKID);
        CEP.TRC(SCCGWA, EQCO8303_OPT_8303.EQ_AC);
        CEP.TRC(SCCGWA, EQCO8303_OPT_8303.EQ_ACT);
        CEP.TRC(SCCGWA, EQCO8303_OPT_8303.EQ_CINO);
        CEP.TRC(SCCGWA, EQCO8303_OPT_8303.PSBK_NO);
        CEP.TRC(SCCGWA, EQCO8303_OPT_8303.PLG_NUM);
        CEP.TRC(SCCGWA, EQCO8303_OPT_8303.PLG_NO);
        CEP.TRC(SCCGWA, EQCO8303_OPT_8303.EQ_QTY);
        CEP.TRC(SCCGWA, EQCO8303_OPT_8303.PLG_QTY);
        CEP.TRC(SCCGWA, EQCO8303_OPT_8303.REL_QTY);
        CEP.TRC(SCCGWA, EQCO8303_OPT_8303.GRT_AMT);
        CEP.TRC(SCCGWA, EQCO8303_OPT_8303.ASMT_AMT);
        CEP.TRC(SCCGWA, EQCO8303_OPT_8303.EFF_DT);
        CEP.TRC(SCCGWA, EQCO8303_OPT_8303.REL_DT);
        CEP.TRC(SCCGWA, EQCO8303_OPT_8303.REL_NUM);
        CEP.TRC(SCCGWA, EQCO8303_OPT_8303.PLG_STS);
        CEP.TRC(SCCGWA, EQCO8303_OPT_8303.REMARK);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT_9;
        SCCFMT.DATA_PTR = EQCO8303_OPT_8303;
        SCCFMT.DATA_LEN = 0;
        CEP.TRC(SCCGWA, SCCFMT.DATA_LEN);
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B026_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQCO8304_OPT_8304);
        IBS.init(SCCGWA, EQRACT);
        EQRACT.KEY.EQ_BKID = EQRPLGD.KEY.EQ_BKID;
        EQRACT.KEY.EQ_AC = EQRPLGD.KEY.EQ_AC;
        T003_READ_EQTACT_BY_EQAC();
        if (pgmRtn) return;
        T000_READ_EQTBVT_FIRST();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CICCUST);
        CICCUST.DATA.CI_NO = EQRACT.CI_NO;
        CICCUST.FUNC = 'C';
        S000_CALL_CIZCUST();
        if (pgmRtn) return;
        if (CICCUST.RC.RC_CODE == 0) {
            EQCO8304_OPT_8304.EQ_CNM = CICCUST.O_DATA.O_CI_NM;
            EQCO8304_OPT_8304.ID_TYP = CICCUST.O_DATA.O_ID_TYPE;
            EQCO8304_OPT_8304.ID_NO = CICCUST.O_DATA.O_ID_NO;
        } else {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_CINO_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        EQCO8304_OPT_8304.EQ_BKID = EQRPLGD.KEY.EQ_BKID;
        EQCO8304_OPT_8304.PLG_NO = EQRPLGD.KEY.PLG_NO;
        EQCO8304_OPT_8304.PROC_FLG = EQRPLGD.PROC_FLG;
        EQCO8304_OPT_8304.EQ_AC = EQRPLGD.KEY.EQ_AC;
        EQCO8304_OPT_8304.EQ_ACT = EQRACT.EQ_ACT;
        EQCO8304_OPT_8304.EQ_CINO = EQRACT.EQ_CINO;
        EQCO8304_OPT_8304.CI_NO = EQRACT.CI_NO;
        EQCO8304_OPT_8304.PSBK_NO = EQRBVT.KEY.PSBK_NO;
        EQCO8304_OPT_8304.PPLG_QTY = EQRPLGD.PREV_PLG_QTY;
        EQCO8304_OPT_8304.TXN_QTY = EQRPLGD.TXN_QTY;
        EQCO8304_OPT_8304.TXN_DT = EQRPLGD.KEY.TXN_DATE;
        EQCO8304_OPT_8304.JRN_NO = EQRPLGD.JRN_NO;
        EQCO8304_OPT_8304.UPT_NO = EQRPLGD.UPT_NO;
        EQCO8304_OPT_8304.RVS_FLG = EQRPLGD.RVS_FLG;
        EQCO8304_OPT_8304.REMARK = EQRPLGD.REMARK;
        CEP.TRC(SCCGWA, EQCO8304_OPT_8304.EQ_CNM);
        CEP.TRC(SCCGWA, EQCO8304_OPT_8304.ID_TYP);
        CEP.TRC(SCCGWA, EQCO8304_OPT_8304.ID_NO);
        CEP.TRC(SCCGWA, EQCO8304_OPT_8304.EQ_BKID);
        CEP.TRC(SCCGWA, EQCO8304_OPT_8304.PLG_NO);
        CEP.TRC(SCCGWA, EQCO8304_OPT_8304.PROC_FLG);
        CEP.TRC(SCCGWA, EQCO8304_OPT_8304.EQ_AC);
        CEP.TRC(SCCGWA, EQCO8304_OPT_8304.EQ_ACT);
        CEP.TRC(SCCGWA, EQCO8304_OPT_8304.EQ_CINO);
        CEP.TRC(SCCGWA, EQCO8304_OPT_8304.CI_NO);
        CEP.TRC(SCCGWA, EQCO8304_OPT_8304.PSBK_NO);
        CEP.TRC(SCCGWA, EQCO8304_OPT_8304.PPLG_QTY);
        CEP.TRC(SCCGWA, EQCO8304_OPT_8304.TXN_QTY);
        CEP.TRC(SCCGWA, EQCO8304_OPT_8304.TXN_DT);
        CEP.TRC(SCCGWA, EQCO8304_OPT_8304.JRN_NO);
        CEP.TRC(SCCGWA, EQCO8304_OPT_8304.UPT_NO);
        CEP.TRC(SCCGWA, EQCO8304_OPT_8304.RVS_FLG);
        CEP.TRC(SCCGWA, EQCO8304_OPT_8304.REMARK);
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, EQCO8304_OPT_8304);
        SCCMPAG.DATA_LEN = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_CIZCUST() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-CUST", CICCUST);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void T000_READ_EQTBVT_FIRST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRBVT);
        EQRBVT.KEY.EQ_BKID = EQRACT.KEY.EQ_BKID;
        EQRBVT.KEY.EQ_AC = EQRACT.KEY.EQ_AC;
        T000_READ_EQTBVT();
        if (pgmRtn) return;
        if (WS_BVT_FLG == 'N' 
            && !EQCMPPLG.DATA.EQ_BKID.equalsIgnoreCase(K_BSZ_BANKID)) {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_BVT_NOT_FOUND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void T003_READ_EQTACT_BY_EQAC() throws IOException,SQLException,Exception {
        EQTACT_RD = new DBParm();
        EQTACT_RD.TableName = "EQTACT";
        EQTACT_RD.where = "EQ_BKID = :EQRACT.KEY.EQ_BKID "
            + "AND EQ_AC = :EQRACT.KEY.EQ_AC";
        EQTACT_RD.fst = true;
        IBS.READ(SCCGWA, EQRACT, this, EQTACT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ACT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ACT_FLG = 'N';
        } else {
        }
    }
    public void T000_READ_EQTBVT() throws IOException,SQLException,Exception {
        EQTBVT_RD = new DBParm();
        EQTBVT_RD.TableName = "EQTBVT";
        EQTBVT_RD.where = "EQ_BKID = :EQRBVT.KEY.EQ_BKID "
            + "AND EQ_AC = :EQRBVT.KEY.EQ_AC "
            + "AND PSBK_STS = :WS_NORMAL_STS";
        EQTBVT_RD.fst = true;
        IBS.READ(SCCGWA, EQRBVT, this, EQTBVT_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_BVT_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_BVT_FLG = 'N';
        } else {
        }
    }
    public void T003_STARTBR_EQTPLGD() throws IOException,SQLException,Exception {
        EQTPLGD_BR.rp = new DBParm();
        EQTPLGD_BR.rp.TableName = "EQTPLGD";
        EQTPLGD_BR.rp.where = "EQ_BKID = :EQRPLGD.KEY.EQ_BKID "
            + "AND EQ_AC = :EQRPLGD.KEY.EQ_AC "
            + "AND PLG_NO = :EQRPLGD.KEY.PLG_NO";
        IBS.STARTBR(SCCGWA, EQRPLGD, this, EQTPLGD_BR);
    }
    public void T000_READNEXT_EQTPLG() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, EQRPLG, this, EQTPLG_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PLG_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_PLG_FLG = 'N';
        } else {
        }
    }
    public void T000_READNEXT_EQTPLGD() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, EQRPLGD, this, EQTPLGD_BR);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_PLGD_FLG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_PLGD_FLG = 'N';
        } else {
        }
    }
    public void T000_GET_PLGNO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = "EQ";
        BPCSGSEQ.CODE = "PLGSEQ";
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        CEP.TRC(SCCGWA, BPCSGSEQ.TYPE);
        CEP.TRC(SCCGWA, BPCSGSEQ.CODE);
        CEP.TRC(SCCGWA, BPCSGSEQ.AC_DATE);
        CEP.TRC(SCCGWA, BPCSGSEQ.RUN_MODE);
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
        CEP.TRC(SCCGWA, BPCSGSEQ.RC.RC_CODE);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (EQCMPPLG.DATA.PLG_NO == null) EQCMPPLG.DATA.PLG_NO = "";
        JIBS_tmp_int = EQCMPPLG.DATA.PLG_NO.length();
        for (int i=0;i<15-JIBS_tmp_int;i++) EQCMPPLG.DATA.PLG_NO += " ";
        EQCMPPLG.DATA.PLG_NO = JIBS_tmp_str[0].substring(0, 4) + EQCMPPLG.DATA.PLG_NO.substring(4);
        JIBS_tmp_str[0] = "" + BPCSGSEQ.SEQ;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<15-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (EQCMPPLG.DATA.PLG_NO == null) EQCMPPLG.DATA.PLG_NO = "";
        JIBS_tmp_int = EQCMPPLG.DATA.PLG_NO.length();
        for (int i=0;i<15-JIBS_tmp_int;i++) EQCMPPLG.DATA.PLG_NO += " ";
        EQCMPPLG.DATA.PLG_NO = EQCMPPLG.DATA.PLG_NO.substring(0, 5 - 1) + JIBS_tmp_str[0].substring(7 - 1, 7 + 9 - 1) + EQCMPPLG.DATA.PLG_NO.substring(5 + 9 - 1);
        CEP.TRC(SCCGWA, EQCMPPLG.DATA.PLG_NO);
    }
    public void S000_CALL_EQZRACT() throws IOException,SQLException,Exception {
        EQCRACT.REC_PTR = EQRACT;
        EQCRACT.REC_LEN = 724;
        IBS.CALLCPN(SCCGWA, "EQ-RSC-EQTACT", EQCRACT);
        if (EQCRACT.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_NOTFND);
        }
    }
    public void R000_WRITE_EQTPLG() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRPLG);
        EQRPLG.KEY.EQ_BKID = EQCMPPLG.DATA.EQ_BKID;
        EQRPLG.KEY.EQ_AC = EQRACT.KEY.EQ_AC;
        EQRPLG.PLG_NO = EQCMPPLG.DATA.PLG_NO;
        EQRPLG.PLG_QTY = EQCMPPLG.DATA.PLG_QTY;
        EQRPLG.GRT_AMT = EQCMPPLG.DATA.GRT_AMT;
        EQRPLG.ASMT_AMT = EQCMPPLG.DATA.ASMT_AMT;
        EQRPLG.EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        EQRPLG.PLG_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        EQRPLG.CONF_TYPE = K_EQPLG_CONF_TYPE;
        EQRPLG.PLG_NUM = EQCMPPLG.DATA.PLG_NUM;
        EQRPLG.PLG_STS = K_EQPLG_STS_NORMAL;
        EQRPLG.CHNL_NO = SCCGWA.COMM_AREA.CHNL;
        EQRPLG.REMARK = EQCMPPLG.DATA.REMARK;
        EQRPLG.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRPLG.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRPLG.OWNER_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        EQRPLG.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRPLG.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRPLG.TS = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = EQRPLG.TS.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) EQRPLG.TS = "0" + EQRPLG.TS;
        T000_WRITE_EQTPLG();
        if (pgmRtn) return;
    }
    public void T000_WRITE_EQTPLG() throws IOException,SQLException,Exception {
        EQTPLG_RD = new DBParm();
        EQTPLG_RD.TableName = "EQTPLG";
        IBS.WRITE(SCCGWA, EQRPLG, EQTPLG_RD);
    }
    public void R000_WRITE_EQTPLGD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQRPLGD);
        CEP.TRC(SCCGWA, EQCMPPLG.FUNC);
        if (EQCMPPLG.FUNC == 'A') {
            EQRPLGD.PROC_FLG = '1';
            EQRPLGD.TXN_QTY = EQCMPPLG.DATA.PLG_QTY;
            EQRPLGD.UPT_NO = EQCMPPLG.DATA.PLG_NUM;
        } else if (EQCMPPLG.FUNC == 'M') {
            EQRPLGD.PROC_FLG = '2';
            EQRPLGD.TXN_QTY = EQCMPPLG.DATA.REL_QTY;
            EQRPLGD.UPT_NO = EQCMPPLG.DATA.REL_NUM;
        } else {
            WS_ERR_MSG = EQCMSG_ERROR_MSG.EQ_INVALID_FUNC;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, EQRACT.PLG_QTY);
        CEP.TRC(SCCGWA, EQCMPPLG.DATA.PLG_QTY);
        CEP.TRC(SCCGWA, EQCMPPLG.DATA.REL_QTY);
        EQRPLGD.KEY.EQ_BKID = EQCMPPLG.DATA.EQ_BKID;
        EQRPLGD.KEY.EQ_AC = EQRACT.KEY.EQ_AC;
        EQRPLGD.KEY.PLG_NO = EQCMPPLG.DATA.PLG_NO;
        EQRPLGD.KEY.TXN_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRPLGD.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        EQRPLGD.PREV_PLG_QTY = EQRACT.PLG_QTY;
        EQRPLGD.UPT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        EQRPLGD.RVS_FLG = K_EQ_RVS_FLG;
        EQRPLGD.REMARK = EQCMPPLG.DATA.REMARK;
        EQRPLGD.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRPLGD.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRPLGD.OWNER_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        EQRPLGD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRPLGD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQRPLGD.TS = "" + SCCGWA.COMM_AREA.TR_TIME;
        JIBS_tmp_int = EQRPLGD.TS.length();
        for (int i=0;i<6-JIBS_tmp_int;i++) EQRPLGD.TS = "0" + EQRPLGD.TS;
        T000_WRITE_EQTPLGD();
        if (pgmRtn) return;
    }
    public void T000_READ_UPDATE_PROC() throws IOException,SQLException,Exception {
        EQTPLG_RD = new DBParm();
        EQTPLG_RD.TableName = "EQTPLG";
        EQTPLG_RD.upd = true;
        IBS.READ(SCCGWA, EQRPLG, EQTPLG_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, EQCMSG_ERROR_MSG.EQ_PLG_NOTFND);
        }
    }
    public void T000_REWRITE_REC_PROC() throws IOException,SQLException,Exception {
        EQRACT.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        EQRACT.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        EQTPLG_RD = new DBParm();
        EQTPLG_RD.TableName = "EQTPLG";
        IBS.REWRITE(SCCGWA, EQRPLG, EQTPLG_RD);
    }
    public void R000_WRT_HIS_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.FMT_ID = "EQPLG";
        BPCPNHIS.INFO.FMT_ID_LEN = 724;
        BPCPNHIS.INFO.CI_NO = EQRACT.CI_NO;
        BPCPNHIS.INFO.AC = EQRACT.KEY.EQ_AC;
        BPCPNHIS.INFO.TX_TYP_CD = WS_TX_MMO;
        BPCPNHIS.INFO.CCY = "156";
        BPCPNHIS.INFO.CCY_TYPE = '1';
        BPCPNHIS.INFO.OLD_DAT_PT = EQRACTO;
        BPCPNHIS.INFO.NEW_DAT_PT = EQRACTN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCPNHIS.RC);
        }
    }
    public void T000_WRITE_EQTPLGD() throws IOException,SQLException,Exception {
        EQTPLGD_RD = new DBParm();
        EQTPLGD_RD.TableName = "EQTPLGD";
        IBS.WRITE(SCCGWA, EQRPLGD, EQTPLGD_RD);
    }
    public void T000_ENDBR_EQTPLG() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, EQTPLG_BR);
    }
    public void T000_ENDBR_EQTPLGD() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, EQTPLGD_BR);
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
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.MSG_PROC_AREA.MSG_TYPE);
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
