package com.hisun.DD;

import com.hisun.TD.*;
import com.hisun.DC.*;
import com.hisun.CI.*;
import com.hisun.SC.*;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;
import com.hisun.BP.*;

import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class DDZSZFJZ {
    int JIBS_tmp_int;
    DBParm DDTCCY_RD;
    DBParm TDTSMST_RD;
    DBParm DDTHLDR_RD;
    DBParm DDTHLD_RD;
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "DD847";
    String WS_ERR_MSG = " ";
    String WS_HLD_NO_SZ = " ";
    DDZSZFJZ_WS_HLD_NO_TLR WS_HLD_NO_TLR = new DDZSZFJZ_WS_HLD_NO_TLR();
    String WS_FRM_APP = " ";
    int WS_P_ROW = 0;
    int WS_P_NUM = 0;
    int WS_T_PAGE = 0;
    int WS_L_ROW = 0;
    String WS_BVT_AC = " ";
    int WS_NUM1 = 0;
    int WS_TIME = 0;
    DDCMSG_ERROR_MSG DDCMSG_ERROR_MSG = new DDCMSG_ERROR_MSG();
    TDCMSG_ERROR_MSG TDCMSG_ERROR_MSG = new TDCMSG_ERROR_MSG();
    DCCMSG_ERROR_MSG DCCMSG_ERROR_MSG = new DCCMSG_ERROR_MSG();
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    CICQACAC CICQACAC = new CICQACAC();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    DDRCCY DDRCCY = new DDRCCY();
    DDRHLD DDRHLD = new DDRHLD();
    TDRSMST TDRSMST = new TDRSMST();
    DDRHLDR DDRHLDR = new DDRHLDR();
    String WS_IAACR_VIA_AC = " ";
    SCCGWA SCCGWA;
    DDCSZFJZ DDCSZFJZ;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    public void MP(SCCGWA SCCGWA, DDCSZFJZ DDCSZFJZ) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.DDCSZFJZ = DDCSZFJZ;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "DDZSZFJZ return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, DDCSZFJZ.FUNC);
        CEP.TRC(SCCGWA, DDCSZFJZ.DATA.HLD_NO);
        CEP.TRC(SCCGWA, DDCSZFJZ.DATA.AC);
        CEP.TRC(SCCGWA, DDCSZFJZ.DATA.AC_SEQ);
        CEP.TRC(SCCGWA, DDCSZFJZ.DATA.ACO_AC);
        CEP.TRC(SCCGWA, DDCSZFJZ.DATA.CCY);
        CEP.TRC(SCCGWA, DDCSZFJZ.DATA.CCY_TYPE);
        CEP.TRC(SCCGWA, DDCSZFJZ.DATA.RSN);
        CEP.TRC(SCCGWA, DDCSZFJZ.DATA.RMK);
        CEP.TRC(SCCGWA, DDCSZFJZ.DATA.EXP_DT);
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B010_GET_ACAC_PROC();
        if (pgmRtn) return;
        B020_FUNC_PROC();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
        if (DDCSZFJZ.FUNC == ' ') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_FUNC_M_INPUT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSZFJZ.FUNC == '1') {
            DDCSZFJZ.FUNC = 'Q';
        } else {
            if (DDCSZFJZ.FUNC == '2') {
                DDCSZFJZ.FUNC = 'A';
            } else {
                if (DDCSZFJZ.FUNC == '3') {
                    DDCSZFJZ.FUNC = 'C';
                }
            }
        }
        if (DDCSZFJZ.FUNC == 'A' 
            && DDCSZFJZ.DATA.EXP_DT < SCCGWA.COMM_AREA.AC_DATE) {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_EXP_DATE_LT_EFFDT;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDCSZFJZ.FUNC == 'A') {
            if (DDCSZFJZ.DATA.AC.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_NO_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSZFJZ.DATA.CCY.trim().length() == 0) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSZFJZ.DATA.CCY_TYPE == ' ') {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_CCY_TYPE_M_INPUT;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
            if (DDCSZFJZ.DATA.EXP_DT == 0) {
                DDCSZFJZ.DATA.EXP_DT = 99991231;
            }
        }
    }
    public void B020_FUNC_PROC() throws IOException,SQLException,Exception {
        if (DDCSZFJZ.FUNC == 'A') {
            B020_10_ADD_AC_STS_PROC();
            if (pgmRtn) return;
        } else if (DDCSZFJZ.FUNC == 'C') {
            B020_20_DEL_AC_STS_PROC();
            if (pgmRtn) return;
        } else if (DDCSZFJZ.FUNC == 'Q') {
            B020_30_QRY_AC_STS_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, SCCEXCP);
            SCCEXCP.MSG_TEXT.KEY_OR_OTHER = "INVALID FUNC(" + DDCSZFJZ.FUNC + ")";
            CEP.EXCP(SCCGWA, SCCEXCP.MSG_TEXT.KEY_OR_OTHER);
        }
    }
    public void B020_10_ADD_AC_STS_PROC() throws IOException,SQLException,Exception {
        if (WS_FRM_APP.equalsIgnoreCase("DD")) {
            R000_READUP_DDTCCY();
            if (pgmRtn) return;
        } else {
            if (WS_FRM_APP.equalsIgnoreCase("TD")) {
                R000_READUP_TDTSMST();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = CICMSG_ERROR_MSG.CI_FRM_APP_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        R000_READ_DDTHLD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_HLD_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            IBS.init(SCCGWA, DDRHLD);
            B020_GEN_HLDNO_PROC();
            if (pgmRtn) return;
            DDRHLD.KEY.HLD_NO = WS_HLD_NO_SZ;
            DDRHLD.OWNER_BK = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            DDRHLD.AC = DDCSZFJZ.DATA.ACO_AC;
            DDRHLD.CARD_NO = DDCSZFJZ.DATA.AC;
            DDRHLD.OWNER_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            DDRHLD.OWNER_BRDP = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            DDRHLD.HLD_CLS = '7';
            DDRHLD.SPR_BR_TYP = '2';
            DDRHLD.CCY = DDCSZFJZ.DATA.CCY;
            DDRHLD.CCY_TYPE = DDCSZFJZ.DATA.CCY_TYPE;
            DDRHLD.EFF_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRHLD.EXP_DATE = DDCSZFJZ.DATA.EXP_DT;
            DDRHLD.HLD_STS = 'N';
            DDRHLD.HLD_RSN = DDCSZFJZ.DATA.RSN;
            DDRHLD.REMARK = DDCSZFJZ.DATA.RMK;
            DDRHLD.SPR_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            DDRHLD.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            DDRHLD.SPR_CHNL = SCCGWA.COMM_AREA.CHNL;
            DDRHLD.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRHLD.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_WRITE_DDTHLD();
            if (pgmRtn) return;
        }
        if (WS_FRM_APP.equalsIgnoreCase("DD")) {
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 65 - 1) + "1" + DDRCCY.STS_WORD.substring(65 + 1 - 1);
            DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTCCY();
            if (pgmRtn) return;
        } else {
            if (WS_FRM_APP.equalsIgnoreCase("TD")) {
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                TDRSMST.STSW = TDRSMST.STSW.substring(0, 9 - 1) + "1" + TDRSMST.STSW.substring(9 + 1 - 1);
                TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
            } else {
                WS_ERR_MSG = CICMSG_ERROR_MSG.CI_FRM_APP_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        R000_WRITE_DDTHLDR();
        if (pgmRtn) return;
        B099_OUTPUT_PROC();
        if (pgmRtn) return;
    }
    public void B020_20_DEL_AC_STS_PROC() throws IOException,SQLException,Exception {
        R000_READUP_DDTHLD_1();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG != '0') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_HLD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        } else {
            DDRHLD.EXP_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRHLD.HLD_STS = 'C';
            DDRHLD.REL_RSN = DDCSZFJZ.DATA.RSN;
            DDRHLD.REMARK = DDCSZFJZ.DATA.RMK;
            DDRHLD.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            DDRHLD.SPR_CHNL = SCCGWA.COMM_AREA.CHNL;
            DDRHLD.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRHLD.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
            DDRHLD.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRHLD.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTHLD();
            if (pgmRtn) return;
        }
        if (WS_FRM_APP.equalsIgnoreCase("DD")) {
            R000_READUP_DDTCCY_1();
            if (pgmRtn) return;
            if (DDRCCY.STS_WORD == null) DDRCCY.STS_WORD = "";
            JIBS_tmp_int = DDRCCY.STS_WORD.length();
            for (int i=0;i<99-JIBS_tmp_int;i++) DDRCCY.STS_WORD += " ";
            DDRCCY.STS_WORD = DDRCCY.STS_WORD.substring(0, 65 - 1) + "0" + DDRCCY.STS_WORD.substring(65 + 1 - 1);
            DDRCCY.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
            DDRCCY.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
            T000_REWRITE_DDTCCY();
            if (pgmRtn) return;
        } else {
            if (WS_FRM_APP.equalsIgnoreCase("TD")) {
                R000_READUP_TDTSMST_1();
                if (pgmRtn) return;
                if (TDRSMST.STSW == null) TDRSMST.STSW = "";
                JIBS_tmp_int = TDRSMST.STSW.length();
                for (int i=0;i<99-JIBS_tmp_int;i++) TDRSMST.STSW += " ";
                TDRSMST.STSW = TDRSMST.STSW.substring(0, 9 - 1) + "0" + TDRSMST.STSW.substring(9 + 1 - 1);
                TDRSMST.UPD_DATE = SCCGWA.COMM_AREA.AC_DATE;
                TDRSMST.UPD_TLT = SCCGWA.COMM_AREA.TL_ID;
                T000_REWRITE_TDTSMST();
                if (pgmRtn) return;
            } else {
                WS_ERR_MSG = CICMSG_ERROR_MSG.CI_FRM_APP_ERR;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
        R000_WRITE_DDTHLDR();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.JRN_NO);
        R000_NHIS_PROC();
        if (pgmRtn) return;
        B099_OUTPUT_PROC_1();
        if (pgmRtn) return;
    }
    public void B020_30_QRY_AC_STS_PROC() throws IOException,SQLException,Exception {
        R000_READUP_DDTHLD_1();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            B099_OUTPUT_PROC_2();
            if (pgmRtn) return;
        } else {
        }
    }
    public void B010_GET_ACAC_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CICQACAC);
        if (DDCSZFJZ.DATA.ACO_AC.trim().length() > 0) {
            CICQACAC.DATA.ACAC_NO = DDCSZFJZ.DATA.ACO_AC;
            CICQACAC.FUNC = 'A';
        } else {
            CICQACAC.FUNC = 'R';
            CICQACAC.DATA.AGR_SEQ = DDCSZFJZ.DATA.AC_SEQ;
            CICQACAC.DATA.CCY_ACAC = DDCSZFJZ.DATA.CCY;
            CICQACAC.DATA.CR_FLG = DDCSZFJZ.DATA.CCY_TYPE;
            CICQACAC.DATA.AGR_NO = DDCSZFJZ.DATA.AC;
        }
        S000_CALL_CIZQACAC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO);
        DDCSZFJZ.DATA.ACO_AC = CICQACAC.O_DATA.O_ACAC_DATA.O_ACAC_NO;
        DDCSZFJZ.DATA.AC = CICQACAC.O_DATA.O_ACR_DATA.O_AGR_NO;
        WS_FRM_APP = CICQACAC.O_DATA.O_ACAC_DATA.O_FRM_APP_ACAC;
    }
    public void R000_READUP_DDTCCY() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = DDCSZFJZ.DATA.ACO_AC;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRCCY.STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_READUP_TDTSMST() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = DDCSZFJZ.DATA.ACO_AC;
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_NOT_FROZEN;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDRSMST.ACO_STS == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_READUP_DDTCCY_1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRCCY);
        DDRCCY.KEY.AC = DDCSZFJZ.DATA.ACO_AC;
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        DDTCCY_RD.upd = true;
        IBS.READ(SCCGWA, DDRCCY, DDTCCY_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_MST1_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (DDRCCY.STS == 'C') {
            WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_AC_CLOSE;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_READUP_TDTSMST_1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, TDRSMST);
        TDRSMST.KEY.ACO_AC = DDCSZFJZ.DATA.ACO_AC;
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        TDTSMST_RD.upd = true;
        IBS.READ(SCCGWA, TDRSMST, TDTSMST_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_REC_NOTFND;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        if (TDRSMST.ACO_STS == '1') {
            WS_ERR_MSG = TDCMSG_ERROR_MSG.TD_AC_IS_CLOSED;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void R000_WRITE_DDTHLDR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLDR);
        DDRHLDR.KEY.HLD_NO = DDRHLD.KEY.HLD_NO;
        DDRHLDR.KEY.TR_DATE = SCCGWA.COMM_AREA.TR_DATE;
        DDRHLDR.KEY.TR_JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        DDRHLDR.OWNER_BK = DDRHLD.OWNER_BK;
        DDRHLDR.HLD_TYP = '5';
        DDRHLDR.AC = DDRHLD.AC;
        DDRHLDR.OWNER_BR = DDRHLD.OWNER_BR;
        DDRHLDR.OWNER_BRDP = DDRHLD.OWNER_BRDP;
        DDRHLDR.BEF_TR_AMT = DDRHLD.HLD_AMT;
        DDRHLDR.CHG_RSN = DDCSZFJZ.DATA.RSN;
        DDRHLDR.CRT_CHNL = DDRHLD.SPR_CHNL;
        DDRHLDR.CRT_BR = DDRHLD.CRT_BR;
        DDRHLDR.CRT_DATE = DDRHLD.CRT_DATE;
        DDRHLDR.CRT_TLR = DDRHLD.CRT_TLR;
        DDRHLDR.UPDTBL_DATE = DDRHLD.UPDTBL_DATE;
        DDRHLDR.UPDTBL_TLR = DDRHLD.UPDTBL_TLR;
        T000_WRITE_DDTHLDR();
        if (pgmRtn) return;
    }
    public void R000_NHIS_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_TYP_CD = "P166";
        BPCPNHIS.INFO.AC = DDCSZFJZ.DATA.AC;
        BPCPNHIS.INFO.TX_CD = "0115847";
        BPCPNHIS.INFO.FMT_ID = K_OUTPUT_FMT;
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void T000_REWRITE_TDTSMST() throws IOException,SQLException,Exception {
        TDTSMST_RD = new DBParm();
        TDTSMST_RD.TableName = "TDTSMST";
        IBS.REWRITE(SCCGWA, TDRSMST, TDTSMST_RD);
    }
    public void T000_WRITE_DDTHLDR() throws IOException,SQLException,Exception {
        DDTHLDR_RD = new DBParm();
        DDTHLDR_RD.TableName = "DDTHLDR";
        IBS.WRITE(SCCGWA, DDRHLDR, DDTHLDR_RD);
    }
    public void R000_READ_DDTHLD() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.AC = DDCSZFJZ.DATA.ACO_AC;
        DDRHLD.CCY = DDCSZFJZ.DATA.CCY;
        DDRHLD.CCY_TYPE = DDCSZFJZ.DATA.CCY_TYPE;
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.where = "AC = :DDRHLD.AC "
            + "AND HLD_CLS = '7' "
            + "AND SPR_BR_TYP = '2' "
            + "AND HLD_STS = 'N'";
        IBS.READ(SCCGWA, DDRHLD, this, DDTHLD_RD);
    }
    public void R000_READUP_DDTHLD_1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDRHLD);
        DDRHLD.AC = DDCSZFJZ.DATA.ACO_AC;
        CEP.TRC(SCCGWA, DDRHLD.AC);
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.where = "AC = :DDRHLD.AC "
            + "AND HLD_CLS = '7' "
            + "AND SPR_BR_TYP = '2' "
            + "AND HLD_STS = 'N'";
        DDTHLD_RD.upd = true;
        IBS.READ(SCCGWA, DDRHLD, this, DDTHLD_RD);
        if (DDCSZFJZ.DATA.HLD_NO.trim().length() > 0) {
            if (!DDCSZFJZ.DATA.HLD_NO.equalsIgnoreCase(DDRHLD.KEY.HLD_NO)) {
                WS_ERR_MSG = DDCMSG_ERROR_MSG.DD_HLD_REC_NOTFND;
                S000_ERR_MSG_PROC();
                if (pgmRtn) return;
            }
        }
    }
    public void B099_OUTPUT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSZFJZ.O_DATA);
        DDCSZFJZ.O_DATA.O_HLD_NO = WS_HLD_NO_SZ;
        DDCSZFJZ.O_DATA.O_AC = DDCSZFJZ.DATA.AC;
        DDCSZFJZ.O_DATA.O_HLD_CLS = '7';
        DDCSZFJZ.O_DATA.O_HLD_STS = 'N';
        DDCSZFJZ.O_DATA.O_SPR_TYP = '2';
        DDCSZFJZ.O_DATA.O_CCY = DDCSZFJZ.DATA.CCY;
        DDCSZFJZ.O_DATA.O_CCY_TYPE = DDCSZFJZ.DATA.CCY_TYPE;
        DDCSZFJZ.O_DATA.O_EFF_DT = SCCGWA.COMM_AREA.AC_DATE;
        DDCSZFJZ.O_DATA.O_EXP_DT = DDCSZFJZ.DATA.EXP_DT;
        DDCSZFJZ.O_DATA.O_RSN = DDCSZFJZ.DATA.RSN;
        DDCSZFJZ.O_DATA.O_RMK = DDCSZFJZ.DATA.RMK;
        DDCSZFJZ.O_DATA.O_HLD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
    }
    public void B099_OUTPUT_PROC_1() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSZFJZ.O_DATA);
        DDCSZFJZ.O_DATA.O_HLD_NO = DDCSZFJZ.DATA.HLD_NO;
        DDCSZFJZ.O_DATA.O_AC = DDRHLD.CARD_NO;
        DDCSZFJZ.O_DATA.O_HLD_CLS = '7';
        DDCSZFJZ.O_DATA.O_HLD_STS = 'C';
        DDCSZFJZ.O_DATA.O_SPR_TYP = '2';
        DDCSZFJZ.O_DATA.O_CCY = DDRHLD.CCY;
        DDCSZFJZ.O_DATA.O_CCY_TYPE = DDRHLD.CCY_TYPE;
        DDCSZFJZ.O_DATA.O_EFF_DT = DDRHLD.EFF_DATE;
        DDCSZFJZ.O_DATA.O_EXP_DT = SCCGWA.COMM_AREA.AC_DATE;
        DDCSZFJZ.O_DATA.O_RSN = DDCSZFJZ.DATA.RSN;
        DDCSZFJZ.O_DATA.O_RMK = DDCSZFJZ.DATA.RMK;
        DDCSZFJZ.O_DATA.O_HLD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCSZFJZ.O_DATA;
        SCCFMT.DATA_LEN = 370;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void B099_OUTPUT_PROC_2() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DDCSZFJZ.O_DATA);
        if (DDCSZFJZ.DATA.HLD_NO.trim().length() > 0) {
            DDCSZFJZ.O_DATA.O_HLD_NO = DDCSZFJZ.DATA.HLD_NO;
            DDCSZFJZ.O_DATA.O_AC = DDRHLD.CARD_NO;
        } else {
            DDCSZFJZ.O_DATA.O_HLD_NO = DDRHLD.KEY.HLD_NO;
            DDCSZFJZ.O_DATA.O_AC = DDCSZFJZ.DATA.AC;
        }
        DDCSZFJZ.O_DATA.O_AC_SEQ = CICQACAC.O_DATA.O_ACAC_DATA.O_AGR_SEQ;
        CEP.TRC(SCCGWA, DDCSZFJZ.O_DATA.O_AC_SEQ);
        DDCSZFJZ.O_DATA.O_HLD_CLS = DDRHLD.HLD_CLS;
        DDCSZFJZ.O_DATA.O_HLD_STS = DDRHLD.HLD_STS;
        DDCSZFJZ.O_DATA.O_SPR_TYP = DDRHLD.SPR_BR_TYP;
        DDCSZFJZ.O_DATA.O_CCY = DDRHLD.CCY;
        DDCSZFJZ.O_DATA.O_CCY_TYPE = DDRHLD.CCY_TYPE;
        DDCSZFJZ.O_DATA.O_EFF_DT = DDRHLD.EFF_DATE;
        DDCSZFJZ.O_DATA.O_EXP_DT = DDRHLD.EXP_DATE;
        if (DDRHLD.HLD_STS == 'N') {
            DDCSZFJZ.O_DATA.O_RSN = DDRHLD.HLD_RSN;
        } else {
            DDCSZFJZ.O_DATA.O_RSN = DDRHLD.REL_RSN;
        }
        DDCSZFJZ.O_DATA.O_RMK = DDRHLD.REMARK;
        DDCSZFJZ.O_DATA.O_HLD_BR = DDRHLD.SPR_BR;
        DDCSZFJZ.O_DATA.TOTAL_PAGE = 1;
        DDCSZFJZ.O_DATA.TOTAL_NUM = 1;
        DDCSZFJZ.O_DATA.CURR_PAGE = 1;
        DDCSZFJZ.O_DATA.PAGE_ROW = 1;
        DDCSZFJZ.O_DATA.LAST_PAGE = 'Y';
        CEP.TRC(SCCGWA, DDCSZFJZ.O_DATA.O_HLD_NO);
        CEP.TRC(SCCGWA, DDCSZFJZ.O_DATA.O_AC);
        CEP.TRC(SCCGWA, DDCSZFJZ.O_DATA.O_CCY);
        CEP.TRC(SCCGWA, DDCSZFJZ.O_DATA.O_CCY_TYPE);
        IBS.init(SCCGWA, SCCFMT);
        SCCFMT.FMTID = K_OUTPUT_FMT;
        SCCFMT.DATA_PTR = DDCSZFJZ.O_DATA;
        SCCFMT.DATA_LEN = 370;
        IBS.FMT(SCCGWA, SCCFMT);
    }
    public void T000_REWRITE_DDTCCY() throws IOException,SQLException,Exception {
        DDTCCY_RD = new DBParm();
        DDTCCY_RD.TableName = "DDTCCY";
        IBS.REWRITE(SCCGWA, DDRCCY, DDTCCY_RD);
    }
    public void T000_REWRITE_DDTHLD() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        IBS.REWRITE(SCCGWA, DDRHLD, DDTHLD_RD);
    }
    public void T000_WRITE_DDTHLD() throws IOException,SQLException,Exception {
        DDTHLD_RD = new DBParm();
        DDTHLD_RD.TableName = "DDTHLD";
        DDTHLD_RD.errhdl = true;
        IBS.WRITE(SCCGWA, DDRHLD, DDTHLD_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            WS_ERR_MSG = DCCMSG_ERROR_MSG.DC_HLDR_REC_EXIST;
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_CIZQACAC() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "CI-INQ-ACAC", CICQACAC);
    }
    public void B020_GEN_HLDNO_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = "SEQ";
        BPCSGSEQ.CODE = "HOLD";
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        IBS.CALLCPN(SCCGWA, "BP-S-GET-SEQ", BPCSGSEQ);
        if (BPCSGSEQ.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCSGSEQ.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
        JIBS_tmp_str[0] = "" + SCCGWA.COMM_AREA.AC_DATE;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) WS_HLD_NO_TLR.WS_HLD_YYMM = 0;
        else WS_HLD_NO_TLR.WS_HLD_YYMM = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        WS_HLD_NO_SZ = IBS.CLS2CPY(SCCGWA, WS_HLD_NO_TLR);
        WS_HLD_NO_TLR.WS_HLD_SEQ = (int) BPCSGSEQ.SEQ;
        WS_HLD_NO_SZ = IBS.CLS2CPY(SCCGWA, WS_HLD_NO_TLR);
    }
    public void S000_ERR_MSG_PROC() throws IOException,SQLException,Exception {
        CEP.ERR(SCCGWA, WS_ERR_MSG);
    }
    public void S000_CALL_SCZGJRN() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCCALL);
        SCCCALL.CPN_NAME = "SC-GET-JOURNAL-NO";
        SCCCALL.COMMAREA_PTR = null;
        SCCCALL.CONTINUE_FLG = 'Y';
        IBS.CALL(SCCGWA, SCCCALL);
    }
    public void S000_CALL_BPZPNHIS() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-REC-NHIS", BPCPNHIS);
        if (BPCPNHIS.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPNHIS.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
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
