package com.hisun.BP;

import com.hisun.SC.*;
import java.lang.reflect.Method;

import java.io.IOException;
import java.sql.SQLException;

public class BPZSPLIA {
    int JIBS_tmp_int;
    DBParm BPTORGR_RD;
    brParm BPTADTL_BR = new brParm();
    String JIBS_tmp_str[] = new String[10];
    boolean pgmRtn = false;
    String CPN_R_TLR_MAINTAIN = "BP-R-TLR-MAINTAIN";
    String CPN_R_STARTBR_APLI = "BP-R-STARTBR-APLI";
    String CPN_R_ADW_TLVB = "BP-R-ADW-TLVB";
    String CPN_P_INQ_ORG = "BP-P-INQ-ORG";
    String CPN_F_BV_PRM_QUERY = "BP-F-BV-PRM-QUERY";
    String CPN_F_TLR_QRY_BR_CHK = "BP-F-TLR-QRY-BR-CHK";
    BPZSPLIA_WS_TEMP_VARIABLE WS_TEMP_VARIABLE = new BPZSPLIA_WS_TEMP_VARIABLE();
    String WS_ERR_MSG = " ";
    char WS_TBL_FLAG = ' ';
    char WS_FOUND1_FLG = ' ';
    char WS_FOUND2_FLG = ' ';
    int WS_STR_NO = 0;
    int WS_END_NO = 0;
    String WS_STR_1 = " ";
    String WS_STR_2 = " ";
    BPCMSG_ERROR_MSG BPCMSG_ERROR_MSG = new BPCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCFMT SCCFMT = new SCCFMT();
    SCCMPAG SCCMPAG = new SCCMPAG();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    BPCTLVBF BPCTLVBF = new BPCTLVBF();
    BPCOTLIB BPCOTLIB = new BPCOTLIB();
    BPCRAPLI BPCRAPLI = new BPCRAPLI();
    BPCOLVBO BPCOLVBO = new BPCOLVBO();
    BPCOAPOO BPCOAPOO = new BPCOAPOO();
    BPRAPLI BPRAPLI = new BPRAPLI();
    BPRADTL BPRADTL = new BPRADTL();
    BPRORGR BPRORGR = new BPRORGR();
    BPCPQORG BPCPQORG = new BPCPQORG();
    BPCFBVQU BPCFBVQU = new BPCFBVQU();
    BPCFTLCM BPCFTLCM = new BPCFTLCM();
    SCCGWA SCCGWA;
    BPCSPLIA BPCSPLIA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCOWA SCCOWA;
    public void MP(SCCGWA SCCGWA, BPCSPLIA BPCSPLIA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        this.BPCSPLIA = BPCSPLIA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        if (pgmRtn) return;
        B000_MAIN_PROC();
        if (pgmRtn) return;
        CEP.TRC(SCCGWA, "BPZSPLIA return!");
        Z_RET();
        if (pgmRtn) return;
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, BPCSPLIA);
        WS_TBL_FLAG = ' ';
        B010_STARTBR_PROCESS();
        if (pgmRtn) return;
        WS_TBL_FLAG = 'Y';
        B020_READNEXT_PROCESS();
        if (pgmRtn) return;
        WS_TEMP_VARIABLE.WS_CNT = 0;
        CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_CNT);
        CEP.TRC(SCCGWA, "WS-TBL-FLAG1");
        CEP.TRC(SCCGWA, WS_TBL_FLAG);
        while (WS_TBL_FLAG != 'N' 
            && WS_TEMP_VARIABLE.WS_CNT <= 100) {
            CEP.TRC(SCCGWA, "WS-TBL-FLAG2");
            CEP.TRC(SCCGWA, WS_TBL_FLAG);
            WS_TEMP_VARIABLE.WS_CNT = WS_TEMP_VARIABLE.WS_CNT + 1;
            CEP.TRC(SCCGWA, WS_TEMP_VARIABLE.WS_CNT);
            if (WS_TEMP_VARIABLE.WS_CNT == 1) {
                B040_01_OUT_TITLE();
                if (pgmRtn) return;
            }
            BPRADTL.KEY.APP_NO = BPRAPLI.KEY.APP_NO;
            BPRADTL.KEY.BV_CODE = BPCSPLIA.BV_CODE;
            if (BPRADTL.KEY.APP_NO == 0 
                || BPRADTL.KEY.APP_NO == ' ') {
                CEP.TRC(SCCGWA, "YYYYYYYYY ZERO");
                WS_STR_NO = 0;
                WS_END_NO = 999999999;
            } else {
                CEP.TRC(SCCGWA, "ZZZZZZZZZZ NOT ZERO");
                WS_STR_NO = BPRADTL.KEY.APP_NO;
                WS_END_NO = BPRADTL.KEY.APP_NO;
            }
            if (BPRADTL.KEY.BV_CODE.equalsIgnoreCase("0") 
                || BPRADTL.KEY.BV_CODE.trim().length() == 0) {
                CEP.TRC(SCCGWA, "XXXXXXXXXXXXXXX");
                WS_STR_1 = "" + 0X00;
                JIBS_tmp_int = WS_STR_1.length();
                for (int i=0;i<0-JIBS_tmp_int;i++) WS_STR_1 = "0" + WS_STR_1;
                WS_STR_2 = "" + 0XFF;
                JIBS_tmp_int = WS_STR_2.length();
                for (int i=0;i<0-JIBS_tmp_int;i++) WS_STR_2 = "0" + WS_STR_2;
            } else {
                CEP.TRC(SCCGWA, "AAAAAAAAAAAAAAA");
                WS_STR_1 = BPRADTL.KEY.BV_CODE;
                WS_STR_2 = BPRADTL.KEY.BV_CODE;
            }
            T000_STARTBR_BPTADTL();
            if (pgmRtn) return;
            T000_READNEXT_BPTADTL();
            if (pgmRtn) return;
            for (WS_TEMP_VARIABLE.WS_CNT = 1; WS_TBL_FLAG != 'N'; WS_TEMP_VARIABLE.WS_CNT += 1) {
                CEP.TRC(SCCGWA, BPRAPLI.APP_BR);
                if ((BPRAPLI.APP_BR != SCCGWA.COMM_AREA.BR_DP.TR_BRANCH)) {
                    WS_FOUND1_FLG = 'N';
                    WS_FOUND2_FLG = 'N';
                    IBS.init(SCCGWA, BPRORGR);
                    BPRORGR.KEY.TYP = "09";
                    BPRORGR.KEY.BR = BPRAPLI.APP_BR;
                    BPRORGR.REL_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    CEP.TRC(SCCGWA, BPRORGR.KEY.BR);
                    CEP.TRC(SCCGWA, BPRORGR.REL_BR);
                    BPTORGR_RD = new DBParm();
                    BPTORGR_RD.TableName = "BPTORGR";
                    BPTORGR_RD.where = "TYP = :BPRORGR.KEY.TYP "
                        + "AND BR = :BPRORGR.KEY.BR "
                        + "AND REL_BR = :BPRORGR.REL_BR";
                    IBS.READ(SCCGWA, BPRORGR, this, BPTORGR_RD);
                    CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
                    if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                        WS_FOUND1_FLG = 'Y';
                    } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    }
                    IBS.init(SCCGWA, BPRORGR);
                    BPRORGR.KEY.TYP = "09";
                    BPRORGR.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    BPRORGR.REL_BR = BPRAPLI.APP_BR;
                    BPTORGR_RD = new DBParm();
                    BPTORGR_RD.TableName = "BPTORGR";
                    BPTORGR_RD.where = "TYP = :BPRORGR.KEY.TYP "
                        + "AND BR = :BPRORGR.KEY.BR "
                        + "AND REL_BR = :BPRORGR.REL_BR";
                    IBS.READ(SCCGWA, BPRORGR, this, BPTORGR_RD);
                    if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                        WS_FOUND2_FLG = 'Y';
                    } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    }
                    if (WS_FOUND1_FLG == 'Y' 
                        || WS_FOUND2_FLG == 'Y') {
                        B040_02_OUT_BRW_DATA();
                        if (pgmRtn) return;
                    }
                } else {
                    WS_FOUND1_FLG = 'N';
                    WS_FOUND2_FLG = 'N';
                    IBS.init(SCCGWA, BPRORGR);
                    BPRORGR.KEY.TYP = "09";
                    BPRORGR.KEY.BR = BPRAPLI.UP_BR;
                    BPRORGR.REL_BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    CEP.TRC(SCCGWA, BPRORGR.KEY.BR);
                    CEP.TRC(SCCGWA, BPRORGR.REL_BR);
                    BPTORGR_RD = new DBParm();
                    BPTORGR_RD.TableName = "BPTORGR";
                    BPTORGR_RD.where = "TYP = :BPRORGR.KEY.TYP "
                        + "AND BR = :BPRORGR.KEY.BR "
                        + "AND REL_BR = :BPRORGR.REL_BR";
                    IBS.READ(SCCGWA, BPRORGR, this, BPTORGR_RD);
                    if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                        WS_FOUND1_FLG = 'Y';
                    } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    }
                    IBS.init(SCCGWA, BPRORGR);
                    BPRORGR.KEY.TYP = "09";
                    BPRORGR.KEY.BR = SCCGWA.COMM_AREA.BR_DP.TR_BRANCH;
                    BPRORGR.REL_BR = BPRAPLI.UP_BR;
                    BPTORGR_RD = new DBParm();
                    BPTORGR_RD.TableName = "BPTORGR";
                    BPTORGR_RD.where = "TYP = :BPRORGR.KEY.TYP "
                        + "AND BR = :BPRORGR.KEY.BR "
                        + "AND REL_BR = :BPRORGR.REL_BR";
                    IBS.READ(SCCGWA, BPRORGR, this, BPTORGR_RD);
                    if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
                        WS_FOUND2_FLG = 'Y';
                    } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
                    }
                    if (WS_FOUND1_FLG == 'Y' 
                        || WS_FOUND2_FLG == 'Y') {
                        B040_02_OUT_BRW_DATA();
                        if (pgmRtn) return;
                    }
                }
                T000_READNEXT_BPTADTL();
                if (pgmRtn) return;
            }
            T000_ENDBR_BPTADTL();
            if (pgmRtn) return;
            B020_READNEXT_PROCESS();
            if (pgmRtn) return;
        }
        B030_ENDBR_PROCESS();
        if (pgmRtn) return;
    }
    public void T000_STARTBR_BPTADTL() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, WS_STR_NO);
        CEP.TRC(SCCGWA, WS_STR_NO);
        CEP.TRC(SCCGWA, WS_END_NO);
        CEP.TRC(SCCGWA, WS_END_NO);
        CEP.TRC(SCCGWA, BPRADTL.KEY.BV_CODE);
        BPTADTL_BR.rp = new DBParm();
        BPTADTL_BR.rp.TableName = "BPTADTL";
        BPTADTL_BR.rp.where = "( APP_NO >= :WS_STR_NO "
            + "AND APP_NO <= :WS_END_NO ) "
            + "AND ( BV_CODE >= :WS_STR_1 "
            + "AND BV_CODE <= :WS_STR_2 )";
        IBS.STARTBR(SCCGWA, BPRADTL, this, BPTADTL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
        }
    }
    public void T000_READNEXT_BPTADTL() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, BPRADTL, this, BPTADTL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
        }
    }
    public void T000_ENDBR_BPTADTL() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, BPTADTL_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_TBL_FLAG = 'Y';
        } else if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_TBL_FLAG = 'N';
        } else {
        }
    }
    public void B010_STARTBR_PROCESS() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPRAPLI);
        IBS.init(SCCGWA, BPCRAPLI);
        CEP.TRC(SCCGWA, "DEV");
        BPRAPLI.KEY.APP_NO = BPCSPLIA.APP_NO;
        BPRAPLI.APP_BR = BPCSPLIA.APP_BR;
        BPRAPLI.UP_BR = BPCSPLIA.BR;
        BPCRAPLI.INFO.BEG_DT = BPCSPLIA.BEG_DT;
        BPCRAPLI.INFO.END_DT = BPCSPLIA.END_DT;
        if (BPCSPLIA.APP_TYPE != ' ') {
            BPRAPLI.APP_TYPE = BPCSPLIA.APP_TYPE;
        } else {
            BPRAPLI.APP_TYPE = ALL.charAt(0);
        }
        if (BPCSPLIA.APP_STS != ' ') {
            BPRAPLI.APP_STS = BPCSPLIA.APP_STS;
        } else {
            BPRAPLI.APP_STS = ALL.charAt(0);
        }
        BPCRAPLI.INFO.FUNC = 'B';
        BPCRAPLI.INFO.POINTER = BPRAPLI;
        BPCRAPLI.INFO.LEN = 1197;
        S000_CALL_BPZRAPLI();
        if (pgmRtn) return;
    }
    public void B020_READNEXT_PROCESS() throws IOException,SQLException,Exception {
        BPCRAPLI.INFO.FUNC = 'N';
        BPCRAPLI.INFO.POINTER = BPRAPLI;
        BPCRAPLI.INFO.LEN = 1197;
        CEP.TRC(SCCGWA, BPCRAPLI.INFO.LEN);
        S000_CALL_BPZRAPLI();
        if (pgmRtn) return;
        if (BPCRAPLI.RETURN_INFO == 'F') {
            WS_TBL_FLAG = 'Y';
        } else if (BPCRAPLI.RETURN_INFO == 'N') {
            WS_TBL_FLAG = 'N';
        } else {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR);
        }
    }
    public void B030_ENDBR_PROCESS() throws IOException,SQLException,Exception {
        BPCRAPLI.INFO.FUNC = 'E';
        BPCRAPLI.INFO.LEN = 1197;
        BPCRAPLI.INFO.POINTER = BPRAPLI;
        S000_CALL_BPZRAPLI();
        if (pgmRtn) return;
        if (BPCRAPLI.RETURN_INFO != 'F') {
            CEP.ERR(SCCGWA, BPCMSG_ERROR_MSG.BP_DATE_RANGE_ERR);
        }
    }
    public void B040_01_OUT_TITLE() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'S';
        SCCMPAG.TITL = " ";
        SCCMPAG.SUBT_ROW_CNT = 0;
        SCCMPAG.MAX_COL_NO = 839;
        CEP.TRC(SCCGWA, SCCMPAG.MAX_COL_NO);
        SCCMPAG.SCR_ROW_CNT = 0;
        SCCMPAG.SCR_COL_CNT = 0;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZFTLCM() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_TLR_QRY_BR_CHK, BPCFTLCM);
        if (BPCFTLCM.RC.RC_CODE != 0) {
            CEP.ERR(SCCGWA, BPCFTLCM.RC.RC_CODE);
        }
    }
    public void B040_02_OUT_BRW_DATA() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, BPCOAPOO);
        BPCOAPOO.APP_NO = BPRAPLI.KEY.APP_NO;
        BPCOAPOO.BR = BPRAPLI.UP_BR;
        BPCOAPOO.APP_TYPE = BPRAPLI.APP_TYPE;
        BPCOAPOO.APP_BR = BPRAPLI.APP_BR;
        BPCOAPOO.APP_STS = BPRAPLI.APP_STS;
        BPCOAPOO.CONF_NO = BPRAPLI.CONF_NO;
        BPCOAPOO.APP_DT = BPRAPLI.APP_DT;
        BPCOAPOO.APP_TLR = BPRAPLI.APP_TLR;
        BPCOAPOO.ACP_DT = BPRAPLI.ACP_DT;
        BPCOAPOO.ACP_TLR = BPRAPLI.ACP_TLR;
        BPCOAPOO.CONF_DT = BPRAPLI.CONF_DT;
        BPCOAPOO.CONF_TLR = BPRAPLI.CONF_TLR;
        BPCOAPOO.UNDO_DT = BPRAPLI.UNDO_DT;
        BPCOAPOO.UNDO_TLR = BPRAPLI.UNDO_TLR;
        BPCOAPOO.BACK_DT = BPRAPLI.BACK_DT;
        BPCOAPOO.BACK_TLR = BPRAPLI.BACK_TLR;
        BPCOAPOO.REJ_DT = BPRAPLI.REJ_DT;
        BPCOAPOO.REJ_TLR = BPRAPLI.REJ_TLR;
        BPCOAPOO.ADT_DT = BPRAPLI.ADT_DT;
        BPCOAPOO.ADT_TLR = BPRAPLI.ADT_TLR;
        BPCOAPOO.OUT_DT = BPRAPLI.OUT_DT;
        BPCOAPOO.OUT_TLR = BPRAPLI.OUT_TLR;
        BPCOAPOO.IN_DT = BPRAPLI.IN_DT;
        BPCOAPOO.IN_TLR = BPRAPLI.IN_TLR;
        BPCOAPOO.APP_NOTE = BPRAPLI.APP_NOTE;
        if (BPRAPLI.APP_STS == '0') {
            BPCOAPOO.UPD_DT = BPRAPLI.APP_DT;
            BPCOAPOO.UPD_TLR = BPRAPLI.APP_TLR;
        } else if (BPRAPLI.APP_STS == '1') {
            BPCOAPOO.UPD_DT = BPRAPLI.ACP_DT;
            BPCOAPOO.UPD_TLR = BPRAPLI.ACP_TLR;
        } else if (BPRAPLI.APP_STS == '2') {
            BPCOAPOO.UPD_DT = BPRAPLI.BACK_DT;
            BPCOAPOO.UPD_TLR = BPRAPLI.BACK_TLR;
        } else if (BPRAPLI.APP_STS == '3') {
            BPCOAPOO.UPD_DT = BPRAPLI.CONF_DT;
            BPCOAPOO.UPD_TLR = BPRAPLI.CONF_TLR;
        } else if (BPRAPLI.APP_STS == '4') {
            BPCOAPOO.UPD_DT = BPRAPLI.ADT_DT;
            BPCOAPOO.UPD_TLR = BPRAPLI.ADT_TLR;
        } else if (BPRAPLI.APP_STS == '5') {
            BPCOAPOO.UPD_DT = BPRAPLI.OUT_DT;
            BPCOAPOO.UPD_TLR = BPRAPLI.OUT_TLR;
        } else if (BPRAPLI.APP_STS == '6') {
            BPCOAPOO.UPD_DT = BPRAPLI.IN_DT;
            BPCOAPOO.UPD_TLR = BPRAPLI.IN_TLR;
        } else if (BPRAPLI.APP_STS == '7') {
            BPCOAPOO.UPD_DT = BPRAPLI.REJ_DT;
            BPCOAPOO.UPD_TLR = BPRAPLI.REJ_TLR;
        }
        CEP.TRC(SCCGWA, BPCOAPOO.UPD_DT);
        CEP.TRC(SCCGWA, BPCOAPOO.UPD_TLR);
        CEP.TRC(SCCGWA, BPCOAPOO.APP_NO);
        CEP.TRC(SCCGWA, BPCOAPOO.BR);
        CEP.TRC(SCCGWA, BPCOAPOO.APP_BR);
        CEP.TRC(SCCGWA, BPCOAPOO.APP_DT);
        CEP.TRC(SCCGWA, BPCOAPOO.APP_TLR);
        CEP.TRC(SCCGWA, BPCOAPOO.APP_NOTE);
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPRAPLI.UP_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        BPCOAPOO.SUPR_NM = BPCPQORG.CHN_NM;
        CEP.TRC(SCCGWA, BPCOAPOO.SUPR_NM);
        IBS.init(SCCGWA, BPCPQORG);
        BPCPQORG.BR = BPRAPLI.APP_BR;
        S000_CALL_BPZPQORG();
        if (pgmRtn) return;
        BPCOAPOO.APP_NM = BPCPQORG.CHN_NM;
        CEP.TRC(SCCGWA, BPCOAPOO.APP_NM);
        BPCOAPOO.APP_NO = BPRADTL.KEY.APP_NO;
        BPCOAPOO.STS = BPRADTL.STS;
        BPCOAPOO.BV_CODE = BPRADTL.KEY.BV_CODE;
        BPCOAPOO.APP_NUM = BPRADTL.APP_NUM;
        BPCOAPOO.HEAD_NO1 = BPRADTL.HEAD_NO1;
        BPCOAPOO.BEG_NO1 = BPRADTL.BEG_NO1;
        BPCOAPOO.END_NO1 = BPRADTL.END_NO1;
        BPCOAPOO.NUM1 = BPRADTL.NUM1;
        BPCOAPOO.HEAD_NO2 = BPRADTL.HEAD_NO2;
        BPCOAPOO.BEG_NO2 = BPRADTL.BEG_NO2;
        BPCOAPOO.END_NO2 = BPRADTL.END_NO2;
        BPCOAPOO.NUM2 = BPRADTL.NUM2;
        BPCOAPOO.HEAD_NO3 = BPRADTL.HEAD_NO3;
        BPCOAPOO.BEG_NO3 = BPRADTL.BEG_NO3;
        BPCOAPOO.END_NO3 = BPRADTL.END_NO3;
        BPCOAPOO.NUM3 = BPRADTL.NUM3;
        BPCOAPOO.HEAD_NO4 = BPRADTL.HEAD_NO4;
        BPCOAPOO.BEG_NO4 = BPRADTL.BEG_NO4;
        BPCOAPOO.END_NO4 = BPRADTL.END_NO4;
        BPCOAPOO.NUM4 = BPRADTL.NUM4;
        BPCOAPOO.OUT_NUM = BPRADTL.OUT_NUM;
        CEP.TRC(SCCGWA, BPRADTL.KEY.APP_NO);
        CEP.TRC(SCCGWA, BPRADTL.KEY.BV_CODE);
        IBS.init(SCCGWA, BPCFBVQU);
        BPCFBVQU.TX_DATA.KEY.CODE = BPCOAPOO.BV_CODE;
        S000_CALL_BPZFBVQU();
        if (pgmRtn) return;
        BPCOAPOO.BV_CNM = BPCFBVQU.TX_DATA.BV_CNM;
        IBS.init(SCCGWA, SCCMPAG);
        SCCMPAG.FUNC = 'D';
        SCCMPAG.DATA_PTR = IBS.CLS2CPY(SCCGWA, BPCOAPOO);
        SCCMPAG.DATA_LEN = 839;
        B_MPAG();
        if (pgmRtn) return;
    }
    public void S000_CALL_BPZPQORG() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, "BP-P-INQ-ORG", BPCPQORG);
        if (BPCPQORG.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCPQORG.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
        }
    }
    public void S000_CALL_BPZFBVQU() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_F_BV_PRM_QUERY, BPCFBVQU);
        if (BPCFBVQU.RC.RC_CODE != 0) {
            WS_ERR_MSG = IBS.CLS2CPY(SCCGWA, BPCFBVQU.RC);
            S000_ERR_MSG_PROC();
            if (pgmRtn) return;
        }
    }
    public void S000_CALL_BPZRAPLI() throws IOException,SQLException,Exception {
        IBS.CALLCPN(SCCGWA, CPN_R_STARTBR_APLI, BPCRAPLI);
        if (BPCRAPLI.RC.RC_CODE != 0) {
            JIBS_tmp_str[0] = IBS.CLS2CPY(SCCGWA, BPCRAPLI.RC);
            CEP.ERR(SCCGWA, JIBS_tmp_str[0]);
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
