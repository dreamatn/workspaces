package com.hisun.CI;

import com.hisun.SC.*;
import com.hisun.TC.XStreamUtil;

import java.io.IOException;
import java.sql.SQLException;

public class CIOT3010 {
    String JIBS_tmp_str[] = new String[10];
    int JIBS_tmp_int;
    DBParm CITTYP_RD;
    brParm CITCMPR_BR = new brParm();
    DBParm CITCMPR_RD;
    String K_PRDP_TYP = "PRDPR";
    String K_IRUL_TYP = "TIRUL";
    String K_AP_MMO = "TD";
    String PGM_SCSSCLDT = "SCSSCLDT";
    String PGM_SCSSCKDT = "SCSSCKDT";
    String WS_MSGID = " ";
    String WS_MMO = " ";
    char WS_EC = ' ';
    char WS_PROC_TYP = ' ';
    short WS_I = 0;
    short WS_J = 0;
    short WS_Y = 0;
    short WS_IDX = 0;
    short WS_X = 0;
    short WS_C = 0;
    short WS_D = 0;
    short WS_A = 0;
    short WS_B = 0;
    short YY = 0;
    short MM = 0;
    short DD = 0;
    short R = 0;
    char WS_MSREL_FLG = ' ';
    char WS_CMPR_FLG = ' ';
    CICMSG_ERROR_MSG CICMSG_ERROR_MSG = new CICMSG_ERROR_MSG();
    SCCCKDT SCCCKDT = new SCCCKDT();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCMSG SCCMSG = new SCCMSG();
    SCCFMT SCCFMT = new SCCFMT();
    SCCCALL SCCCALL = new SCCCALL();
    SCCENPSW SCCENPSW = new SCCENPSW();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCBINF SCCBINF = new SCCBINF();
    CIRTYP CIRTYP = new CIRTYP();
    CIRCMPR CIRCMPR = new CIRCMPR();
    SCCGWA SCCGWA;
    SCCGBPA_BP_AREA GWA_BP_AREA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    CIB3010_AWA_3010 CIB3010_AWA_3010;
    public void MP(SCCGWA SCCGWA) throws IOException,SQLException,Exception {
        this.SCCGWA = SCCGWA;
        CEP.TRC(SCCGWA);
        A000_INIT_PROC();
        B000_MAIN_PROC();
        CEP.TRC(SCCGWA, "CIOT3010 return!");
        Z_RET();
    }
    public void A000_INIT_PROC() throws IOException,SQLException,Exception {
        GWA_SC_AREA = (SCCGSCA_SC_AREA) SCCGWA.SC_AREA_PTR;
        GWA_BP_AREA = (SCCGBPA_BP_AREA) SCCGWA.BP_AREA_PTR;
        SCCGWA.COMM_AREA.AWA_AREA_PTR = SCCGWA.COMM_AREA.AWA_AREA_PTR.replaceAll("BODY>", "CIB3010_AWA_3010>");
        CIB3010_AWA_3010 = (CIB3010_AWA_3010) XStreamUtil.xmlToBean(SCCGWA.COMM_AREA.AWA_AREA_PTR);
        IBS.init(SCCGWA, CIRTYP);
    }
    public void B000_MAIN_PROC() throws IOException,SQLException,Exception {
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.CHNL);
        B100_CHECK_INPUT();
        if (CIB3010_AWA_3010.FUNC == '0') {
            B210_ADD_CITTYP_INFO();
        } else if (CIB3010_AWA_3010.FUNC == '1') {
            B220_UPD_CITTYP_INFO();
        } else if (CIB3010_AWA_3010.FUNC == '2') {
            B230_DEL_CITTYP_INFO();
        } else if (CIB3010_AWA_3010.FUNC == '3') {
            B240_QRY_CITTYP_INFO();
        } else {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_FUNC_ERROR);
        }
    }
    public void B100_CHECK_INPUT() throws IOException,SQLException,Exception {
        if (CIB3010_AWA_3010.TYPE.trim().length() == 0) {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_KIROKU_NOTFND);
        }
        CEP.TRC(SCCGWA, CIB3010_AWA_3010.STA_TM);
        CEP.TRC(SCCGWA, CIB3010_AWA_3010.END_TM);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.AC_DATE);
        CEP.TRC(SCCGWA, CIB3010_AWA_3010.VAL[1-1].CON_TYP);
        CEP.TRC(SCCGWA, CIB3010_AWA_3010.VAL[2-1].CON_TYP);
        CEP.TRC(SCCGWA, CIB3010_AWA_3010.VAL[3-1].CON_TYP);
        CEP.TRC(SCCGWA, CIB3010_AWA_3010.VAL[4-1].CON_TYP);
        CEP.TRC(SCCGWA, CIB3010_AWA_3010.VAL[5-1].CON_TYP);
        CEP.TRC(SCCGWA, CIB3010_AWA_3010.VAL[6-1].CON_TYP);
        CEP.TRC(SCCGWA, CIB3010_AWA_3010.VAL[7-1].CON_TYP);
        if (CIB3010_AWA_3010.FUNC == '0') {
            if ((CIB3010_AWA_3010.STA_TM < SCCGWA.COMM_AREA.AC_DATE) 
                || (CIB3010_AWA_3010.END_TM <= CIB3010_AWA_3010.STA_TM)) {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DATE_ERROR);
            }
        }
        JIBS_tmp_str[0] = "" + CIB3010_AWA_3010.STA_TM;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(0, 4).trim().length() == 0) YY = 0;
        else YY = Short.parseShort(JIBS_tmp_str[0].substring(0, 4));
        JIBS_tmp_str[0] = "" + CIB3010_AWA_3010.STA_TM;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1).trim().length() == 0) MM = 0;
        else MM = Short.parseShort(JIBS_tmp_str[0].substring(5 - 1, 5 + 2 - 1));
        JIBS_tmp_str[0] = "" + CIB3010_AWA_3010.STA_TM;
        JIBS_tmp_int = JIBS_tmp_str[0].length();
        for (int i=0;i<8-JIBS_tmp_int;i++) JIBS_tmp_str[0] = "0"+JIBS_tmp_str[0];
        if (JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1).trim().length() == 0) DD = 0;
        else DD = Short.parseShort(JIBS_tmp_str[0].substring(7 - 1, 7 + 2 - 1));
        R = (short) (YY - ( YY / 400 ) * 400);
        if (R == 0) {
            if (MM == '01') {
                if (DD > 31) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DATE_ERROR);
                }
            } else if (MM == '02') {
                if (DD > 29) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DATE_ERROR);
                }
            } else if (MM == '03') {
                if (DD > 31) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DATE_ERROR);
                }
            } else if (MM == '04') {
                if (DD > 30) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DATE_ERROR);
                }
            } else if (MM == '05') {
                if (DD > 31) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DATE_ERROR);
                }
            } else if (MM == '06') {
                if (DD > 30) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DATE_ERROR);
                }
            } else if (MM == '07') {
                if (DD > 31) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DATE_ERROR);
                }
            } else if (MM == '08') {
                if (DD > 31) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DATE_ERROR);
                }
            } else if (MM == '09') {
                if (DD > 30) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DATE_ERROR);
                }
            } else if (MM == '10') {
                if (DD > 31) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DATE_ERROR);
                }
            } else if (MM == '11') {
                if (DD > 30) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DATE_ERROR);
                }
            } else if (MM == '12') {
                if (DD > 31) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DATE_ERROR);
                }
            } else {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DATE_ERROR);
            }
        } else {
            if (MM == '01') {
                if (DD > 31) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DATE_ERROR);
                }
            } else if (MM == '02') {
                if (DD > 28) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DATE_ERROR);
                }
            } else if (MM == '03') {
                if (DD > 31) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DATE_ERROR);
                }
            } else if (MM == '04') {
                if (DD > 30) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DATE_ERROR);
                }
            } else if (MM == '05') {
                if (DD > 31) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DATE_ERROR);
                }
            } else if (MM == '06') {
                if (DD > 30) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DATE_ERROR);
                }
            } else if (MM == '07') {
                if (DD > 31) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DATE_ERROR);
                }
            } else if (MM == '08') {
                if (DD > 31) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DATE_ERROR);
                }
            } else if (MM == '09') {
                if (DD > 30) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DATE_ERROR);
                }
            } else if (MM == '10') {
                if (DD > 31) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DATE_ERROR);
                }
            } else if (MM == '11') {
                if (DD > 30) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DATE_ERROR);
                }
            } else if (MM == '12') {
                if (DD > 31) {
                    CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DATE_ERROR);
                }
            } else {
                CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DATE_ERROR);
            }
        }
    }
    public void B210_ADD_CITTYP_INFO() throws IOException,SQLException,Exception {
        CIRTYP.KEY.TYPE = CIB3010_AWA_3010.TYPE;
        CIRTYP.NAME = CIB3010_AWA_3010.NAME;
        CIRTYP.OBJ_TYP = CIB3010_AWA_3010.OBJ_TYP;
        CIRTYP.SPE_FLG = CIB3010_AWA_3010.SPE_FLG;
        CIRTYP.SPE_ACT_FLG = CIB3010_AWA_3010.CT_FLG;
        CIRTYP.STA_TM = CIB3010_AWA_3010.STA_TM;
        CIRTYP.END_TM = CIB3010_AWA_3010.END_TM;
        CIRTYP.CON_TYP_SPT = CIB3010_AWA_3010.CON_SPT;
        CIRTYP.CON_VAL_MTH = CIB3010_AWA_3010.CON_MTH;
        CIRTYP.CON_TYP_LW = CIB3010_AWA_3010.CON_LWTP;
        CIRTYP.LMT_TYP = "000000000000000";
        for (WS_A = 1; WS_A < 7; WS_A += 1) {
            WS_B = (short) (WS_A * 2 - 1);
            if (CIB3010_AWA_3010.VAL[WS_A-1].CON_TYP.trim().length() > 0 
                || !CIB3010_AWA_3010.VAL[WS_A-1].CON_TYP.equalsIgnoreCase("0")) {
                CEP.TRC(SCCGWA, WS_A);
                CEP.TRC(SCCGWA, CIB3010_AWA_3010.VAL[WS_A-1].CON_TYP);
                if (CIRTYP.LMT_TYP == null) CIRTYP.LMT_TYP = "";
                JIBS_tmp_int = CIRTYP.LMT_TYP.length();
                for (int i=0;i<15-JIBS_tmp_int;i++) CIRTYP.LMT_TYP += " ";
                if (CIB3010_AWA_3010.VAL[WS_A-1].CON_TYP == null) CIB3010_AWA_3010.VAL[WS_A-1].CON_TYP = "";
                JIBS_tmp_int = CIB3010_AWA_3010.VAL[WS_A-1].CON_TYP.length();
                for (int i=0;i<2-JIBS_tmp_int;i++) CIB3010_AWA_3010.VAL[WS_A-1].CON_TYP += " ";
                CIRTYP.LMT_TYP = CIRTYP.LMT_TYP.substring(0, WS_B - 1) + CIB3010_AWA_3010.VAL[WS_A-1].CON_TYP + CIRTYP.LMT_TYP.substring(WS_B + 2 - 1);
            }
        }
        CIRTYP.HIS_FLG = CIB3010_AWA_3010.HIS_FLG;
        CIRTYP.ONE_FLG = CIB3010_AWA_3010.ONE_FLG;
        CIRTYP.MTX_CON_TYP1 = CIB3010_AWA_3010.MTX_TYP1;
        CIRTYP.MTX_CON_TYP2 = CIB3010_AWA_3010.MTX_TYP2;
        CIRTYP.MTX_CON_TYP3 = CIB3010_AWA_3010.MTX_TYP3;
        CIRTYP.CON_VAL_MTH = "11211111111331111111112100000000000000000000000000";
        CIRTYP.CON_TYP_LW = "10011000000000000000000000000000000000000000000000";
        CIRTYP.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CIRTYP.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
        CIRTYP.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CIRTYP.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        R000_GEN_CMPR_PROC();
        CEP.TRC(SCCGWA, CIRTYP.KEY.TYPE);
        CEP.TRC(SCCGWA, CIRTYP.NAME);
        CEP.TRC(SCCGWA, CIRTYP.OBJ_TYP);
        CEP.TRC(SCCGWA, CIRTYP.SPE_FLG);
        CEP.TRC(SCCGWA, CIRTYP.STA_TM);
        CEP.TRC(SCCGWA, CIRTYP.END_TM);
        CEP.TRC(SCCGWA, CIRTYP.CON_TYP_SPT);
        CEP.TRC(SCCGWA, CIRTYP.CON_VAL_MTH);
        CEP.TRC(SCCGWA, CIRTYP.CON_TYP_LW);
        CEP.TRC(SCCGWA, CIRTYP.LMT_TYP);
        CEP.TRC(SCCGWA, CIRTYP.HIS_FLG);
        CEP.TRC(SCCGWA, CIRTYP.ONE_FLG);
        CEP.TRC(SCCGWA, CIRTYP.MTX_CON_TYP1);
        CEP.TRC(SCCGWA, CIRTYP.MTX_CON_TYP2);
        CEP.TRC(SCCGWA, CIRTYP.MTX_CON_TYP3);
        T000_INSERT_CITTYP();
    }
    public void B220_UPD_CITTYP_INFO() throws IOException,SQLException,Exception {
        CIRTYP.KEY.TYPE = CIB3010_AWA_3010.TYPE;
        T000_READUPD_CITTYP();
        R000_GEN_CMPR_PROC();
        CIRTYP.NAME = CIB3010_AWA_3010.NAME;
        CIRTYP.OBJ_TYP = CIB3010_AWA_3010.OBJ_TYP;
        CIRTYP.SPE_FLG = CIB3010_AWA_3010.SPE_FLG;
        CIRTYP.SPE_ACT_FLG = CIB3010_AWA_3010.CT_FLG;
        CIRTYP.STA_TM = CIB3010_AWA_3010.STA_TM;
        CIRTYP.END_TM = CIB3010_AWA_3010.END_TM;
        CIRTYP.CON_TYP_SPT = CIB3010_AWA_3010.CON_SPT;
        CIRTYP.CON_VAL_MTH = CIB3010_AWA_3010.CON_MTH;
        CIRTYP.CON_TYP_LW = CIB3010_AWA_3010.CON_LWTP;
        CIRTYP.LMT_TYP = "000000000000000";
        for (WS_A = 1; WS_A < 7; WS_A += 1) {
            WS_B = (short) (WS_A * 2 - 1);
            if (CIB3010_AWA_3010.VAL[WS_A-1].CON_TYP.trim().length() > 0) {
                if (CIRTYP.LMT_TYP == null) CIRTYP.LMT_TYP = "";
                JIBS_tmp_int = CIRTYP.LMT_TYP.length();
                for (int i=0;i<15-JIBS_tmp_int;i++) CIRTYP.LMT_TYP += " ";
                if (CIB3010_AWA_3010.VAL[WS_A-1].CON_TYP == null) CIB3010_AWA_3010.VAL[WS_A-1].CON_TYP = "";
                JIBS_tmp_int = CIB3010_AWA_3010.VAL[WS_A-1].CON_TYP.length();
                for (int i=0;i<2-JIBS_tmp_int;i++) CIB3010_AWA_3010.VAL[WS_A-1].CON_TYP += " ";
                CIRTYP.LMT_TYP = CIRTYP.LMT_TYP.substring(0, WS_B - 1) + CIB3010_AWA_3010.VAL[WS_A-1].CON_TYP + CIRTYP.LMT_TYP.substring(WS_B + 2 - 1);
            }
        }
        CIRTYP.HIS_FLG = CIB3010_AWA_3010.HIS_FLG;
        CIRTYP.ONE_FLG = CIB3010_AWA_3010.ONE_FLG;
        CIRTYP.MTX_CON_TYP1 = CIB3010_AWA_3010.MTX_TYP1;
        CIRTYP.MTX_CON_TYP2 = CIB3010_AWA_3010.MTX_TYP2;
        CIRTYP.MTX_CON_TYP3 = CIB3010_AWA_3010.MTX_TYP3;
        CIRTYP.CON_VAL_MTH = "11211111111331111111112100000000000000000000000000";
        CIRTYP.CON_TYP_LW = "10011000000000000000000000000000000000000000000000";
        CIRTYP.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
        CIRTYP.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
        T000_REWRITE_CITTYP();
    }
    public void B230_DEL_CITTYP_INFO() throws IOException,SQLException,Exception {
        IBS.init(SCCGWA, CIRCMPR);
        CIRCMPR.KEY.TYPE = CIB3010_AWA_3010.TYPE;
        T000_STARTBR_CITCMPR();
        T000_READNEXT_CITCMPR();
        while (WS_CMPR_FLG != 'N') {
            T000_DELETE_CITCMPR();
            T000_READNEXT_CITCMPR();
        }
        T000_ENDBR_CITCMPR();
        CIRTYP.KEY.TYPE = CIB3010_AWA_3010.TYPE;
        T000_READUPD_CITTYP();
        T000_DELETE_CITTYP();
    }
    public void B240_QRY_CITTYP_INFO() throws IOException,SQLException,Exception {
        CIRTYP.KEY.TYPE = CIB3010_AWA_3010.TYPE;
        T000_READ_CITTYP();
    }
    public void R000_GEN_CMPR_PROC() throws IOException,SQLException,Exception {
        WS_Y = 1;
        if (CIB3010_AWA_3010.TX_TYPE == null) CIB3010_AWA_3010.TX_TYPE = "";
        JIBS_tmp_int = CIB3010_AWA_3010.TX_TYPE.length();
        for (int i=0;i<50-JIBS_tmp_int;i++) CIB3010_AWA_3010.TX_TYPE += " ";
        for (WS_I = 1; WS_I <= 25 
            && CIB3010_AWA_3010.TX_TYPE.substring(WS_Y - 1, WS_Y + 1 - 1).trim().length() != 0; WS_I += 1) {
            IBS.init(SCCGWA, CIRCMPR);
            if (CIB3010_AWA_3010.TX_TYPE == null) CIB3010_AWA_3010.TX_TYPE = "";
            JIBS_tmp_int = CIB3010_AWA_3010.TX_TYPE.length();
            for (int i=0;i<50-JIBS_tmp_int;i++) CIB3010_AWA_3010.TX_TYPE += " ";
            CIRCMPR.KEY.TX_TYPE = CIB3010_AWA_3010.TX_TYPE.substring(WS_Y - 1, WS_Y + 2 - 1);
            CIRCMPR.KEY.TYPE = CIB3010_AWA_3010.TYPE;
            CEP.TRC(SCCGWA, CIB3010_AWA_3010.TX_TYPE);
            CEP.TRC(SCCGWA, CIRCMPR.KEY.TX_TYPE);
            CEP.TRC(SCCGWA, CIRCMPR.KEY.TYPE);
            CEP.TRC(SCCGWA, WS_Y);
            if (!CIRCMPR.KEY.TX_TYPE.equalsIgnoreCase("00")) {
                T000_READ_CITCMPR();
                if (WS_CMPR_FLG == 'N') {
                    CIRCMPR.AVL_FLG = '0';
                    CIRCMPR.STA_DT = CIB3010_AWA_3010.STA_TM;
                    CIRCMPR.END_DT = CIB3010_AWA_3010.END_TM;
                    CIRCMPR.CRT_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    CIRCMPR.CRT_TLR = SCCGWA.COMM_AREA.TL_ID;
                    CIRCMPR.UPDTBL_DATE = SCCGWA.COMM_AREA.AC_DATE;
                    CIRCMPR.UPDTBL_TLR = SCCGWA.COMM_AREA.TL_ID;
                    T000_INSERT_CITCMPR();
                }
            }
            WS_Y = (short) (WS_I * 2 + 1);
        }
    }
    public void T000_READ_CITTYP() throws IOException,SQLException,Exception {
        CITTYP_RD = new DBParm();
        CITTYP_RD.TableName = "CITTYP";
        CITTYP_RD.where = "TYPE = :CIRTYP.KEY.TYPE";
        IBS.READ(SCCGWA, CIRTYP, this, CITTYP_RD);
    }
    public void T000_READUPD_CITTYP() throws IOException,SQLException,Exception {
        CITTYP_RD = new DBParm();
        CITTYP_RD.TableName = "CITTYP";
        CITTYP_RD.where = "TYPE = :CIRTYP.KEY.TYPE";
        CITTYP_RD.upd = true;
        IBS.READ(SCCGWA, CIRTYP, this, CITTYP_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_DELETE_NOTFND);
        }
    }
    public void T000_REWRITE_CITTYP() throws IOException,SQLException,Exception {
        CITTYP_RD = new DBParm();
        CITTYP_RD.TableName = "CITTYP";
        IBS.REWRITE(SCCGWA, CIRTYP, CITTYP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_INSERT_CITTYP() throws IOException,SQLException,Exception {
        CITTYP_RD = new DBParm();
        CITTYP_RD.TableName = "CITTYP";
        IBS.WRITE(SCCGWA, CIRTYP, CITTYP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INSERT_ERROR);
        }
    }
    public void T000_DELETE_CITTYP() throws IOException,SQLException,Exception {
        CITTYP_RD = new DBParm();
        CITTYP_RD.TableName = "CITTYP";
        IBS.DELETE(SCCGWA, CIRTYP, CITTYP_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_STARTBR_CITCMPR() throws IOException,SQLException,Exception {
        CITCMPR_BR.rp = new DBParm();
        CITCMPR_BR.rp.TableName = "CITCMPR";
        CITCMPR_BR.rp.where = "TYPE = :CIRCMPR.KEY.TYPE";
        CITCMPR_BR.rp.upd = true;
        IBS.STARTBR(SCCGWA, CIRCMPR, this, CITCMPR_BR);
    }
    public void T000_READNEXT_CITCMPR() throws IOException,SQLException,Exception {
        IBS.READNEXT(SCCGWA, CIRCMPR, this, CITCMPR_BR);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '1') {
            WS_CMPR_FLG = 'N';
        }
    }
    public void T000_ENDBR_CITCMPR() throws IOException,SQLException,Exception {
        IBS.ENDBR(SCCGWA, CITCMPR_BR);
    }
    public void T000_DELETE_CITCMPR() throws IOException,SQLException,Exception {
        CITCMPR_RD = new DBParm();
        CITCMPR_RD.TableName = "CITCMPR";
        IBS.DELETE(SCCGWA, CIRCMPR, CITCMPR_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
    }
    public void T000_READ_CITCMPR() throws IOException,SQLException,Exception {
        CITCMPR_RD = new DBParm();
        CITCMPR_RD.TableName = "CITCMPR";
        CITCMPR_RD.where = "TYPE = :CIRCMPR.KEY.TYPE "
            + "AND TX_TYPE = :CIRCMPR.KEY.TX_TYPE";
        IBS.READ(SCCGWA, CIRCMPR, this, CITCMPR_RD);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '0') {
            WS_CMPR_FLG = 'Y';
        } else {
            WS_CMPR_FLG = 'N';
        }
    }
    public void T000_INSERT_CITCMPR() throws IOException,SQLException,Exception {
        CITCMPR_RD = new DBParm();
        CITCMPR_RD.TableName = "CITCMPR";
        IBS.WRITE(SCCGWA, CIRCMPR, CITCMPR_RD);
        CEP.TRC(SCCGWA, SCCGWA.COMM_AREA.DBIO_FLG);
        if (SCCGWA.COMM_AREA.DBIO_FLG == '2') {
            CEP.ERR(SCCGWA, CICMSG_ERROR_MSG.CI_INSERT_ERROR);
        }
    }
    public void Z_RET() throws IOException,SQLException,Exception {
        return;
    }
    public void B_DB_EXCP() throws IOException,SQLException,Exception {
        throw new SQLException(SCCGWA.e);
    }
}
