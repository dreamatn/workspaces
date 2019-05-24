package com.hisun.PS;

import java.io.IOException;
import java.sql.SQLException;

import com.hisun.AI.AICUUPIA;
import com.hisun.BP.BPCPORUP_DATA_INFO;
import com.hisun.DD.DDCUCRAC;
import com.hisun.DD.DDCUDRAC;
import com.hisun.SC.SCCCALL;
import com.hisun.SC.SCCCLDT;
import com.hisun.SC.SCCEXCP;
import com.hisun.SC.SCCFMT;
import com.hisun.SC.SCCGSCA_SC_AREA;
import com.hisun.SC.SCCGWA;
import com.hisun.SC.SCCMSG;

public class PSZOTCRP {
    boolean pgmRtn = false;
    String K_OUTPUT_FMT = "PS110";
    String CPN_U_PSZOTCRP = "PS-P-OTCR-PROC";
    String K_TBL_BCC = "PSTOBLL";
    String WS_ERR_MSG = " ";
    String WS_ERR_INFO = " ";
    String WS_ENCRY_NO = " ";
    int WS_ISS_BR_MSG = 0;
    char WS_TABLE_FLG = ' ';
    PSCMSG_ERROR_MSG PSCMSG_ERROR_MSG = new PSCMSG_ERROR_MSG();
    SCCEXCP SCCEXCP = new SCCEXCP();
    SCCCALL SCCCALL = new SCCCALL();
    SCCMSG SCCMSG = new SCCMSG();
    SCCCLDT SCCCLDT = new SCCCLDT();
    SCCFMT SCCFMT = new SCCFMT();
    DDCSRCHQ DDCSRCHQ = new DDCSRCHQ();
    AICUUPIA AICUUPIA = new AICUUPIA();
    DDCUDRAC DDCUDRAC = new DDCUDRAC();
    DDCUCRAC DDCUCRAC = new DDCUCRAC();
    PSCATRMT PSCATRMT = new PSCATRMT();
    PSREINF PSREINF = new PSREINF();
    PSRPBIN PSRPBIN = new PSRPBIN();
    PSROBLL PSROBLL = new PSROBLL();
    char WK_DC = ' ';
    String WK_CERT_NO = " ";
    String WK_INSNM = " ";
    SCCGWA SCCGWA;
    SCCGSCA_SC_AREA GWA_SC_AREA;
    BPCPORUP_DATA_INFO BPCPORUP;
