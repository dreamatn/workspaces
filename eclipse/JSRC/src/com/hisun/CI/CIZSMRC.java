package com.hisun.CI;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.BP.BPCPNHIS;
import com.hisun.BP.BPCPRMR;
import com.hisun.BP.BPCSGSEQ;
import com.hisun.BP.BPRPRMT;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGBPA_BP_AREA;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMPAG;
import com.hisun.SC.SCCMSG;

public class CIZSMRC {
    boolean pgmRtn = false;
    int K_MAX_ROW = 10;
    int K_MAX_COL = 99;
    int K_COL_STS = 9;
    char WS_HIS_FLG = ' ';
    char WS_IDES_FLG = ' ';
    int WS_I = 0;
    int WS_J = 0;
    short WS_TP_IND = 0;
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCMSG SCCMSG = new SCCMSG();
    BPCSGSEQ BPCSGSEQ = new BPCSGSEQ();
    CICOSMRC CICOSMRC = new CICOSMRC();
    CICOMRC1 CICOMRC1 = new CICOMRC1();
    CICOMRC2 CICOMRC2 = new CICOMRC2();
    CIRRPTY CIRRPTY = new CIRRPTY();
    CIRRIDE CIRRIDE = new CIRRIDE();
    BPCPNHIS BPCPNHIS = new BPCPNHIS();
    BPCPRMR BPCPRMR = new BPCPRMR();
    BPRPRMT BPRPRMT = new BPRPRMT();
    CIRRELT CIRRELT = new CIRRELT();
    CIRRREL CIRRREL = new CIRRREL();
    CIRRREL CIRRRELO = new CIRRREL();
    CIRRREL CIRRRELN = new CIRRREL();
    CIRRELT CIRRELTO = new CIRRELT();
    CIRRELT CIRRELTN = new CIRRELT();
    CIRBAS CIRBAS = new CIRBAS();
    CIRNAM CIRNAM = new CIRNAM();
    String WS_CI_CNM = " ";
    String WS_CI_ENM = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    CICSMRC CICSMRC;
    public void MP(SCCGWA SCCGWA, CICSMRC CICSMRC) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.CICSMRC = CICSMRC;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "CIZSMRC return!");
        Z_RET();
        if (pgmRtn) return;
        JIBS_RETURN();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        B010_CHECK_PROC();
        if (pgmRtn) return;
        if (CICSMRC.FUNC == 'B') {
            B020_BROWSE_PROC();
            if (pgmRtn) return;
        } else if (CICSMRC.FUNC == 'A') {
            B030_ADD_RELT_PROC();
            if (pgmRtn) return;
        } else if (CICSMRC.FUNC == 'R') {
            B040_ADD_RREL_PROC();
            if (pgmRtn) return;
        } else if (CICSMRC.FUNC == 'M') {
            B050_MODIFY_PROC();
            if (pgmRtn) return;
        } else if (CICSMRC.FUNC == 'D') {
            B060_DELETE_PROC();
            if (pgmRtn) return;
        } else if (CICSMRC.FUNC == 'I') {
            B070_INQUIRY_PROC();
            if (pgmRtn) return;
        } else if (CICSMRC.FUNC == 'Q') {
            B080_INQ_IDE_PROC();
            if (pgmRtn) return;
        } else {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_INPUT_DATA_ERR, CICSMRC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
    }
    public void B010_CHECK_PROC() throws IOException,SQLException,Exception {
    }
    public void B020_BROWSE_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRRELT);
        CEP.TRC(SCCGWA, CICSMRC.DATA.RELT_CNM);
        CEP.TRC(SCCGWA, CICSMRC.DATA.RELT_ENM);
        CEP.TRC(SCCGWA, CICSMRC.DATA.RELT_ID_TYPE);
        CEP.TRC(SCCGWA, CICSMRC.DATA.RELT_ID_NO);
        if (CICSMRC.DATA.RELT_CNM.trim().length() > 0 
            || CICSMRC.DATA.RELT_ENM.trim().length() > 0 
            || CICSMRC.DATA.RELT_ID_NO.trim().length() > 0) {
            IBS.init(SCCGWA, CIRRELT);
            CIRRELT.RELT_CNM = CICSMRC.DATA.RELT_CNM;
            CIRRELT.RELT_ENM = CICSMRC.DATA.RELT_ENM;
            CIRRELT.RELT_ID_TYPE = CICSMRC.DATA.RELT_ID_TYPE;
            CIRRELT.RELT_ID_NO = CICSMRC.DATA.RELT_ID_NO;
            T000_STARTBR_CITRELT_BY_NMID();
            if (pgmRtn) return;
            T000_READNEXT_CITRELT();
            if (pgmRtn) return;
            if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_RELT_NOT_FND, CICSMRC.RC);
                Z_RET();
                if (pgmRtn) return;
            }
            B020_01_OUT_TITLE();
            if (pgmRtn) return;
            while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                B020_03_OUT_BRW_DATA1();
                if (pgmRtn) return;
                IBS.init(SCCGWA, CIRRREL);
                IBS.init(SCCGWA, CICOSMRC);
                CIRRREL.RELT_NO = CIRRELT.KEY.RELT_NO;
                T000_STARTBR_CITRREL_BY_RELTNO();
                if (pgmRtn) return;
                T000_READNEXT_CITRREL();
                if (pgmRtn) return;
                while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                    B020_02_OUT_BRW_DATA();
                    if (pgmRtn) return;
                    T000_READNEXT_CITRREL();
                    if (pgmRtn) return;
                }
                T000_READNEXT_CITRELT();
                if (pgmRtn) return;
            }
        } else {
            CEP.TRC(SCCGWA, CICSMRC.DATA.REL_CNM);
            CEP.TRC(SCCGWA, CICSMRC.DATA.REL_ENM);
            CEP.TRC(SCCGWA, CICSMRC.DATA.REL_ID_TYPE);
            CEP.TRC(SCCGWA, CICSMRC.DATA.REL_ID_NO);
            if (CICSMRC.DATA.REL_CNM.trim().length() > 0 
                || CICSMRC.DATA.REL_ENM.trim().length() > 0 
                || CICSMRC.DATA.REL_ID_NO.trim().length() > 0) {
                IBS.init(SCCGWA, CIRRREL);
                CEP.TRC(SCCGWA, CIRRREL.REL_ENM);
                CEP.TRC(SCCGWA, CIRRREL.REL_ID_NO);
                CEP.TRC(SCCGWA, WS_I);
                CIRRREL.REL_CNM = CICSMRC.DATA.REL_CNM;
                CIRRREL.REL_ENM = CICSMRC.DATA.REL_ENM;
                CIRRREL.REL_ID_TYPE = CICSMRC.DATA.REL_ID_TYPE;
                CIRRREL.REL_ID_NO = CICSMRC.DATA.REL_ID_NO;
                T000_STARTBR_CITRREL_BY_IDNM();
                if (pgmRtn) return;
                T000_READNEXT_CITRREL();
                if (pgmRtn) return;
                if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_RREL_NOT_FND, CICSMRC.RC);
                    Z_RET();
                    if (pgmRtn) return;
                }
                CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
                B020_01_OUT_TITLE();
                if (pgmRtn) return;
                while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
                    IBS.init(SCCGWA, CIRRELT);
                    CIRRELT.KEY.RELT_NO = CIRRREL.RELT_NO;
                    T000_READ_CITRELT();
                    if (pgmRtn) return;
                    if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                        IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_RELT_NOT_FND, CICSMRC.RC);
                        Z_RET();
                        if (pgmRtn) return;
                    }
                    B020_02_OUT_BRW_DATA();
                    if (pgmRtn) return;
                    T000_READNEXT_CITRREL();
                    if (pgmRtn) return;
                }
            }
        }
    }
    public void B030_ADD_RELT_PROC() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRRELT);
        IBS.init(SCCGWA, CIRRELTO);
        IBS.init(SCCGWA, CIRRELTN);
        CIRRELT.RELT_CNM = CICSMRC.DATA.RELT_CNM;
        CIRRELT.RELT_ENM = CICSMRC.DATA.RELT_ENM;
        CIRRELT.RELT_ID_TYPE = CICSMRC.DATA.RELT_ID_TYPE;
        CIRRELT.RELT_ID_NO = CICSMRC.DATA.RELT_ID_NO;
        CIRRELT.RELT_TYPE = CICSMRC.DATA.RELT_TYPE;
        T000_READ_CITRELT_BY_IDNM();
        if (pgmRtn) return;
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            IBS.CPY2CLS(SCCGWA, CICMSG_ERROR_MSG.CI_RELT_EXIST, CICSMRC.RC);
            Z_RET();
            if (pgmRtn) return;
        }
        IBS.init(SCCGWA, BPCSGSEQ);
        BPCSGSEQ.TYPE = "SEQ";
        BPCSGSEQ.CODE = "RTNOSEQ";
        BPCSGSEQ.AC_DATE = SCCGWA.COMM_AREA.AC_DATE;
        BPCSGSEQ.RUN_MODE = 'O';
        S000_CALL_BPZSGSEQ();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, BPCSGSEQ.SEQ);
        CIRRELT.KEY.RELT_NO = BPCSGSEQ.SEQ;
        CIRRELT.RELT_CORP_NM = CICSMRC.DATA.RELT_CORP_NM;
        CIRRELT.TITLE = CICSMRC.DATA.TITLE;
        CIRRELT.EMP_NO = CICSMRC.DATA.EMP_NO;
        CIRRELT.EFF_DT = CICSMRC.DATA.EFF_DT;
        CIRRELT.EXP_DT = CICSMRC.DATA.EXP_DT;
        CIRRELT.IDENT_CD = CICSMRC.DATA.IDENT_CD;
        CIRRELT.RELT_CD = CICSMRC.DATA.RELT_CD;
        CIRRELT.RELT_REMARK = CICSMRC.DATA.RELT_REMARK;
        CIRRELT.RULE = CICSMRC.DATA.RULE;
        CIRRELT.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRRELT.UPD_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRRELT.CRT_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRRELT.UPD_DT = SCCGWA.COMM_AREA.AC_DATE;
        CIRRELT.CRT_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        CIRRELT.UPD_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
        T000_WRITE_CITRELT();
        if (pgmRtn) return;
        B031_RRET_PROC();
        if (pgmRtn) return;
        IBS.CLONE(SCCGWA, CIRRELT, CIRRELTO);
        IBS.CLONE(SCCGWA, CIRRELT, CIRRELTN);
        IBS.init(SCCGWA, BPCPNHIS);
        BPCPNHIS.INFO.TX_TYP = 'A';
        WS_HIS_FLG = 'Y';
        R000_SAVE_HIS_PROC();
        if (pgmRtn) return;
    }
    public void B031_RRET_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, CICSMRC.DATA.RELT_TYPE);
        WS_TP_IND = 0;
        if (CICSMRC.DATA.RELT_TYPE.trim().length() == 0) WS_TP_IND = 0;
        else WS_TP_IND = Short.parseShort(CICSMRC.DATA.RELT_TYPE);
        WS_TP_IND = (short) (WS_TP_IND + 10);
        CEP.TRC(SCCGWA, WS_TP_IND);
        IBS.init(SCCGWA, CIRBAS);
        CIRBAS.ID_TYPE = CICSMRC.DATA.RELT_ID_TYPE;
        CIRBAS.ID_NO = CICSMRC.DATA.RELT_ID_NO;
        CEP.TRC(SCCGWA, CIRBAS.CI_NM);
        CEP.TRC(SCCGWA, CIRBAS.ID_TYPE);
        CEP.TRC(SCCGWA, CIRBAS.ID_NO);
        T000_STARTBR_CITBAS();
        if (pgmRtn) return;
        T000_READNEXT_CITBAS();
        if (pgmRtn) return;
        while (SCCGWA.COMM_AREA.DBIO_FLG != '1') {
            CEP.TRC(SCCGWA, CIRBAS.KEY.CI_NO);
            CEP.TRC(SCCGWA, CIRBAS.CI_TYP);
            CEP.TRC(SCCGWA, CIRBAS.CI_ATTR);
            CEP.TRC(SCCGWA, CIRBAS.IDE_STSW);
            if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
            JIBS_tmp_int = CIRBAS.IDE_STSW.length();
            for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
            if (!CIRBAS.IDE_STSW.substring(WS_TP_IND - 1, WS_TP_IND + 1 - 1).equalsIgnoreCase("1") 
                && WS_TP_IND <= 19) {
                if (CIRBAS.IDE_STSW == null) CIRBAS.IDE_STSW = "";
                JIBS_tmp_int = CIRBAS.IDE_STSW.length();
                for (int i=0;i<30-JIBS_tmp_int;i++) CIRBAS.IDE_STSW += " ";
                CIRBAS.IDE_STSW = CIRBAS.IDE_STSW.substring(0, WS_TP_IND - 1) + "1" + CIRBAS.IDE_STSW.substring(WS_TP_IND + 1 - 1);
                CEP.TRC(SCCGWA, CIRBAS.IDE_STSW);
                T000_REWRITE_CITBAS();
                if (pgmRtn) return;
            }
            T000_READNEXT_CITBAS();
            if (pgmRtn) return;
        }
        T000_ENDBR_CITBAS();
        if (pgmRtn) return;
        IBS.init(SCCGWA, CIRNAM);
