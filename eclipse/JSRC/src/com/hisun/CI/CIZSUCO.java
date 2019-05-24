package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCFXCUS;
import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPQORG;
import com.hisun.BP.BPCPRMR;
import com.hisun.BP.BPRPRMT;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class CIZSUCO {
    boolean pgmRtn = false;
    String K_GROWTH_ACCOUNT = "9520000001";
    String K_CITIZEN_CARD = "1203010101";
    String K_ALIEN_CARD = "1203021401";
    double WS_LMT = 0;
    int WS_EFF_DT = 0;
    int WS_EXP_DT = 0;
    int WS_NUM = 0;
    CIZSUCO_WS_CARD_LIMIT[] WS_CARD_LIMIT = new CIZSUCO_WS_CARD_LIMIT[10];
    int WS_LIMIT_NUM = 0;
    char WS_JGL_FLG = ' ';
    char WS_CITIZEN_FLG = ' ';
    int WS_I = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMSG SCCMSG = new SCCMSG();
    SCCMPAG SCCMPAG = new SCCMPAG();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    DDCSZFJZ DDCSZFJZ = new DDCSZFJZ();
    CIRBAS CIRBAS = new CIRBAS();
    CIRBAS CIRBASO = new CIRBAS();
    CIRBAS CIRBASN = new CIRBAS();
    CIRACR CIRACR = new CIRACR();
    CIRACR CIRACRO = new CIRACR();
    CIRACR CIRACRN = new CIRACR();
    CIRACAC CIRACAC = new CIRACAC();
    CIRACAC CIRACACO = new CIRACAC();
    CIRACAC CIRACACN = new CIRACAC();
    CIRAGT CIRAGT = new CIRAGT();
    CIRAGT CIRAGTO = new CIRAGT();
    CIRAGT CIRAGTN = new CIRAGT();
    CIRLIMT CIRLIMT = new CIRLIMT();
    CIRLIMT CIRLIMTO = new CIRLIMT();
    CIRLIMT CIRLIMTN = new CIRLIMT();
    CIRHIS CIRHIS = new CIRHIS();
    CIRHIS CIRHISO = new CIRHIS();
    CIRHIS CIRHISN = new CIRHIS();
    CIRLS2 CIRLS2 = new CIRLS2();
    DCCUCINO DCCUCINO = new DCCUCINO();
    EQCSACT EQCSACT = new EQCSACT();
    BPCFXCUS BPCFXCUS = new BPCFXCUS();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPRMT BPRPRMT = new BPRPRMT();
    CICPCDL CICPCDL = new CICPCDL();
    DCCIQHLD DCCIQHLD = new DCCIQHLD();
    DDCRFBID DDCRFBID = new DDCRFBID();
    DDRFBID DDRFBID = new DDRFBID();
    int WS_AC_DATE = 0;
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSUCO CICSUCO;
    public CIZSUCO() {
        for (int i=0;i<10;i++) WS_CARD_LIMIT[i] = new CIZSUCO_WS_CARD_LIMIT();
    }
    public void MP(SCCGWA SCCGWA, CICSUCO CICSUCO) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSUCO = CICSUCO;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSUCO return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        IBS.init(SCCGWA, CICSUCO.RC);
        WS_AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_INPUT_DATA();
        if (pgmRtn) return;
        B020_CHECK_COMBINE();
        if (pgmRtn) return;
        B070_COMBINE_CITACR();
        if (pgmRtn) return;
        B080_COMBINE_CITACAC();
        if (pgmRtn) return;
        B090_COMBINE_CITAGT();
        if (pgmRtn) return;
        B100_COMBINE_CITLIMT();
        if (pgmRtn) return;
        B220_COMBINE_DC();
        if (pgmRtn) return;
        B240_COMBINE_EQ();
        if (pgmRtn) return;
        B250_COMBINE_FX();
        if (pgmRtn) return;
        B300_WRITE_COMBINE_HIS();
        if (pgmRtn) return;
    }
    public void B010_CHECK_INPUT_DATA() throws IOException,SQLException,Exception {
    }
    public void B020_CHECK_COMBINE() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, "主客户号�?�?");
        CEP.TRC(SCCGWA, CICSUCO.DATA.CI_NO);
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICSUCO.DATA.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "主客户号不存�?");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND);
        }
        if (CIRBAS.CI_TYP != '1') {
            CEP.TRC(SCCGWA, "主客户为非个人客�?");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CITYP_MUST_BE_P);
        }
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        if (CIRBAS.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.TRC(SCCGWA, "暂禁客户不允许合�?");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_STSW_IS_HOLD);
        }
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        if (CIRBAS.STSW.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.TRC(SCCGWA, "死亡客户不允许合�?");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_IS_DEAD);
        }
        CEP.TRC(SCCGWA, "被合并客户号�?�?");
        CEP.TRC(SCCGWA, CICSUCO.DATA.CI_NO1);
        IBS.init(SCCGWA, CIRBAS);
        IBS.init(SCCGWA, CIRBASO);
        IBS.init(SCCGWA, CIRBASN);
        CIRBAS.KEY.CI_NO = CICSUCO.DATA.CI_NO1;
        T000_READ_CITBAS_UPD();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.TRC(SCCGWA, "被合并客户号不存�?");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_BAS_INF_NOTFND);
        }
        if (CIRBAS.CI_TYP != '1') {
            CEP.TRC(SCCGWA, "被合并客户为非个人客�?");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CITYP_MUST_BE_P);
        }
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        if (CIRBAS.STSW.substring(2 - 1, 2 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.TRC(SCCGWA, "暂禁客户不允许合�?");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_STSW_IS_HOLD);
        }
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        if (CIRBAS.STSW.substring(12 - 1, 12 + 1 - 1).equalsIgnoreCase("1")) {
            CEP.TRC(SCCGWA, "死亡客户不允许合�?");
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_IS_DEAD);
        }
        IBS.init(SCCGWA, CIRACR);
        CIRACR.CI_NO = CICSUCO.DATA.CI_NO;
        CIRACR.PROD_CD = K_GROWTH_ACCOUNT;
        T000_READ_CITACR_GROWTH();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.init(SCCGWA, CIRACR);
            CIRACR.CI_NO = CICSUCO.DATA.CI_NO1;
            CIRACR.PROD_CD = K_GROWTH_ACCOUNT;
            T000_READ_CITACR_GROWTH();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                CEP.TRC(SCCGWA, "两个客户均有成长账户，不能合�?");
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_CO_ERR_GROWTH);
            }
        }
        B021_CHECK_AC_STS();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRBAS, CIRBASO);
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        CIRBAS.STSW = "0" + CIRBAS.STSW.substring(1);
        if (CIRBAS.STSW == null) CIRBAS.STSW = "";
        JIBS_tmp_int = CIRBAS.STSW.length();
        for (int i=0;i<80-JIBS_tmp_int;i++) CIRBAS.STSW += " ";
        CIRBAS.STSW = CIRBAS.STSW.substring(0, 23 - 1) + "1" + CIRBAS.STSW.substring(23 + 1 - 1);
        T000_REWRITE_CITBAS();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRBAS, CIRBASN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'M';
        R000_WRT_HIS_PROC_BAS();
        if (pgmRtn) return;
    }
    public void B021_CHECK_AC_STS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACAC);
        CIRACAC.CI_NO = CICSUCO.DATA.CI_NO1;
        T001_STARTBR_CITACAC_BY_CI();
        if (pgmRtn) return;
        T001_READNEXT_CITACAC();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            IBS.init(SCCGWA, DCCIQHLD);
            DCCIQHLD.INP_DATA.AC = CIRACAC.KEY.ACAC_NO;
            S001_CALL_DCZIQHLD();
            if (pgmRtn) return;
            if (DCCIQHLD.OUT_DATA.LAW_AC == 'Y' 
                || DCCIQHLD.OUT_DATA.LAW_AMT == 'Y') {
                CEP.TRC(SCCGWA, CIRACAC.KEY.ACAC_NO);
                CEP.TRC(SCCGWA, "冻结不能被合�?");
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ACCT_EXT_HLD);
                Z_RET();
                if (pgmRtn) return;
            }
            IBS.init(SCCGWA, DDCRFBID);
            IBS.init(SCCGWA, DDRFBID);
            DDRFBID.AC = CIRACAC.KEY.ACAC_NO;
            CEP.TRC(SCCGWA, DDRFBID.AC);
            CEP.TRC(SCCGWA, DDRFBID.TYPE);
            T002_START_BROWSE_FBID();
            if (pgmRtn) return;
            T002_READ_NEXT_FBID();
            if (pgmRtn) return;
            while (DDCRFBID.RETURN_INFO != 'E') {
                if (DDRFBID.ORG_TYP == '2') {
                    T002_END_BROWSE_FBID();
                    if (pgmRtn) return;
                    CEP.TRC(SCCGWA, "外部暂禁不能被合�?");
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_ACCT_EXT_HLD);
                    Z_RET();
                    if (pgmRtn) return;
                }
                T002_READ_NEXT_FBID();
                if (pgmRtn) return;
            }
            T002_END_BROWSE_FBID();
            if (pgmRtn) return;
            T001_READNEXT_CITACAC();
            if (pgmRtn) return;
        }
        T001_ENDBR_CITACAC();
        if (pgmRtn) return;
    }
    public void B070_COMBINE_CITACR() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACR);
        CIRACR.CI_NO = CICSUCO.DATA.CI_NO1;
        T000_STARTBR_CITACR_BY_CI();
        if (pgmRtn) return;
        T000_READNEXT_CITACR();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            IBS.CLONE(SCCGWA, CIRACR, CIRACRO);
            CIRACR.CI_NO = CICSUCO.DATA.CI_NO;
            CIRACR.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRACR.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRACR.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_REWRITE_CITACR();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, CIRACR, CIRACRN);
            T000_READNEXT_CITACR();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACR();
        if (pgmRtn) return;
        R000_CHECK_CARD_NUM();
        if (pgmRtn) return;
    }
    public void B080_COMBINE_CITACAC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRACAC);
        CIRACAC.CI_NO = CICSUCO.DATA.CI_NO1;
        T000_STARTBR_CITACAC_BY_CI();
        if (pgmRtn) return;
        T000_READNEXT_CITACAC();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            IBS.CLONE(SCCGWA, CIRACAC, CIRACACO);
            CIRACAC.CI_NO = CICSUCO.DATA.CI_NO;
            CIRACAC.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRACAC.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRACAC.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_REWRITE_CITACAC();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, CIRACAC, CIRACACN);
            if (CIRACAC.FRM_APP.equalsIgnoreCase("DD") 
                || CIRACAC.FRM_APP.equalsIgnoreCase("TD")) {
                IBS.init(SCCGWA, DDCSZFJZ);
                DDCSZFJZ.FUNC = 'Q';
                DDCSZFJZ.DATA.ACO_AC = CIRACAC.KEY.ACAC_NO;
                S000_CALL_DDZSZFJZ();
                if (pgmRtn) return;
                CEP.TRC(SCCGWA, CIRACAC.KEY.ACAC_NO);
                CEP.TRC(SCCGWA, DDCSZFJZ.O_DATA.O_HLD_CLS);
                if (DDCSZFJZ.O_DATA.O_HLD_CLS == '7') {
                    IBS.init(SCCGWA, DDCSZFJZ);
                    DDCSZFJZ.FUNC = 'C';
                    DDCSZFJZ.DATA.ACO_AC = CIRACAC.KEY.ACAC_NO;
                    S000_CALL_DDZSZFJZ();
                    if (pgmRtn) return;
                }
            }
            T000_READNEXT_CITACAC();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITACAC();
        if (pgmRtn) return;
    }
    public void B090_COMBINE_CITAGT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRAGT);
        CIRAGT.KEY.ENTY_NO = CICSUCO.DATA.CI_NO1;
        CIRAGT.KEY.ENTY_TYP = '0';
        T000_STARTBR_CITAGT_BY_ENTY();
        if (pgmRtn) return;
        T000_READNEXT_CITAGT();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            IBS.CLONE(SCCGWA, CIRAGT, CIRAGTO);
            CIRAGT.KEY.ENTY_NO = CICSUCO.DATA.CI_NO;
            CIRAGT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRAGT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRAGT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_REWRITE_CITAGT();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, CIRAGT, CIRAGTN);
            T000_READNEXT_CITAGT();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITAGT();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRAGT);
        CIRAGT.CI_NO = CICSUCO.DATA.CI_NO1;
        T000_STARTBR_CITAGT_BY_CI();
        if (pgmRtn) return;
        T000_READNEXT_CITAGT();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            IBS.CLONE(SCCGWA, CIRAGT, CIRAGTO);
            CIRAGT.CI_NO = CICSUCO.DATA.CI_NO;
            CIRAGT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
            CIRAGT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
            CIRAGT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
            T000_REWRITE_CITAGT();
            if (pgmRtn) return;
            IBS.CLONE(SCCGWA, CIRAGT, CIRAGTN);
            T000_READNEXT_CITAGT();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITAGT();
        if (pgmRtn) return;
    }
    public void B100_COMBINE_CITLIMT() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRLIMT);
        CIRLIMT.KEY.CI_NO = CICSUCO.DATA.CI_NO1;
        CIRLIMT.KEY.LMT_TP = "01";
        T000_READ_CITLIMT();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0' 
            && CIRLIMT.UPD_DT == SCCGWA.COMM_AREA.AC_DATE) {
            WS_LMT = CIRLIMT.KEY.LMT;
            WS_EFF_DT = CIRLIMT.EFF_DT;
            WS_EXP_DT = CIRLIMT.EXP_DT;
            IBS.init(SCCGWA, CIRLIMT);
            CIRLIMT.KEY.CI_NO = CICSUCO.DATA.CI_NO;
            CIRLIMT.KEY.LMT_TP = "01";
            T000_READ_CITLIMT();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                IBS.CLONE(SCCGWA, CIRLIMT, CIRLIMTO);
                if (CIRLIMT.UPD_DT == SCCGWA.COMM_AREA.AC_DATE) {
                    CIRLIMT.KEY.LMT = CIRLIMT.KEY.LMT + WS_LMT;
                    CIRLIMT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                    CIRLIMT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                    CIRLIMT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    T000_REWRITE_CITLIMT();
                    if (pgmRtn) return;
                    IBS.CLONE(SCCGWA, CIRLIMT, CIRLIMTN);
                } else {
                    CIRLIMT.KEY.LMT = WS_LMT;
                    CIRLIMT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                    CIRLIMT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                    CIRLIMT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    T000_REWRITE_CITLIMT();
                    if (pgmRtn) return;
                    IBS.CLONE(SCCGWA, CIRLIMT, CIRLIMTN);
                }
            } else {
                CIRLIMT.KEY.CI_NO = CICSUCO.DATA.CI_NO;
                CIRLIMT.KEY.LMT_TP = "01";
                CIRLIMT.KEY.LMT = WS_LMT;
                CIRLIMT.CCY = "840";
                CIRLIMT.EFF_DT = WS_EFF_DT;
                CIRLIMT.EXP_DT = WS_EXP_DT;
                CIRLIMT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRLIMT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                CIRLIMT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
                CIRLIMT.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
                CIRLIMT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                CIRLIMT.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                T000_WRITE_CITLIMT();
                if (pgmRtn) return;
                IBS.CLONE(SCCGWA, CIRLIMT, CIRLIMTN);
            }
        }
    }
    public void B300_WRITE_COMBINE_HIS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRHIS);
        CIRHIS.KEY.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CIRHIS.KEY.JRN_NO = SCCGWA.COMM_AREA.JRN_NO;
        CIRHIS.KEY.CI_STS = 'N';
        CIRHIS.KEY.PRI_CI = CICSUCO.DATA.CI_NO1;
        CIRHIS.CI_NO = CICSUCO.DATA.CI_NO;
        CIRHIS.COMB_TYP = CICSUCO.DATA.COMB_TYP;
        CIRHIS.COMB_RES = CICSUCO.DATA.COMB_RES.charAt(0);
        CIRHIS.ELSE_RES = CICSUCO.DATA.ELSE_RES;
        CIRHIS.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRHIS.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRHIS.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRHIS.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRHIS.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRHIS.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_WRITE_CITHIS();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRHIS, CIRHISN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        R000_WRT_HIS_PROC_HIS();
        if (pgmRtn) return;
    }
    public void B220_COMBINE_DC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, DCCUCINO);
        DCCUCINO.FUNC = '1';
        DCCUCINO.CI_NO = CICSUCO.DATA.CI_NO1;
        DCCUCINO.CI_TYP = 'P';
        DCCUCINO.NEW_CI_NO = CICSUCO.DATA.CI_NO;
        S000_CALL_DCZUCINO();
        if (pgmRtn) return;
    }
    public void B240_COMBINE_EQ() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, EQCSACT);
        EQCSACT.FUNC = 'C';
        EQCSACT.DATA.EQ_BKID = "01";
        EQCSACT.DATA.CI_NO = CICSUCO.DATA.CI_NO1;
        EQCSACT.DATA.OCI_NO = CICSUCO.DATA.CI_NO;
        S000_CALL_EQZSACT();
        if (pgmRtn) return;
    }
    public void B250_COMBINE_FX() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCFXCUS);
        BPCFXCUS.FUNC = '1';
        BPCFXCUS.NEW_CI_NO = CICSUCO.DATA.CI_NO;
        BPCFXCUS.OLD_CI_NO = CICSUCO.DATA.CI_NO1;
        S000_CALL_BPZFXCUS();
        if (pgmRtn) return;
    }
    public void R000_WRT_HIS_PROC_BAS() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.FMT_ID = "CIRBAS";
        BPCPNHIS.INFO.FMT_ID_LEN = 568;
        BPCPNHIS.INFO.CI_NO = CICSUCO.DATA.CI_NO1;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRBASO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRBASN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_WRT_HIS_PROC_ACR() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.FMT_ID = "CIRACR";
        BPCPNHIS.INFO.FMT_ID_LEN = 181;
        BPCPNHIS.INFO.CI_NO = CICSUCO.DATA.CI_NO;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRACRO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRACRN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_WRT_HIS_PROC_ACAC() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.FMT_ID = "CIRACAC";
        BPCPNHIS.INFO.FMT_ID_LEN = 242;
        BPCPNHIS.INFO.CI_NO = CICSUCO.DATA.CI_NO;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRACACO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRACACN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_WRT_HIS_PROC_AGT() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.FMT_ID = "CIRAGT";
        BPCPNHIS.INFO.FMT_ID_LEN = 664;
        BPCPNHIS.INFO.CI_NO = CICSUCO.DATA.CI_NO;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRAGTO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRAGTN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_WRT_HIS_PROC_LIMT() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.FMT_ID = "CIRLIMT";
        BPCPNHIS.INFO.FMT_ID_LEN = 288;
        BPCPNHIS.INFO.CI_NO = CICSUCO.DATA.CI_NO;
        BPCPNHIS.INFO.OLD_DAT_PT = CIRLIMTO;
        BPCPNHIS.INFO.NEW_DAT_PT = CIRLIMTN;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_WRT_HIS_PROC_HIS() throws IOException,SQLException,Exception {
        BPCPNHIS.INFO.DATA_FLG = 'Y';
        BPCPNHIS.INFO.JRNNO = SCCGWA.COMM_AREA.JRN_NO;
        BPCPNHIS.INFO.TX_CD = IBS.CLS2CPY(SCCGWA, SCCGWA.COMM_AREA.TR_ID);
        BPCPNHIS.INFO.MAKER_TLR = SCCGWA.COMM_AREA.TL_ID;
        BPCPNHIS.INFO.FMT_ID = "CIRHIS";
        BPCPNHIS.INFO.FMT_ID_LEN = 12;
        BPCPNHIS.INFO.CI_NO = CICSUCO.DATA.CI_NO;
        S000_CALL_BPZPNHIS();
        if (pgmRtn) return;
    }
    public void R000_CHECK_CARD_NUM() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.KEY.CI_NO = CICSUCO.DATA.CI_NO;
        T000_READ_CITBAS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRLS2);
        CIRLS2.KEY.LST_CD = "JGL";
        CIRLS2.KEY.ID_TYPE = CIRBAS.ID_TYPE;
        CIRLS2.KEY.ID_NO = CIRBAS.ID_NO;
